// on connection, first get list of all orgs, project, teams user is in and use that to subscribe
var __assign = (this && this.__assign) || function () {
    __assign = Object.assign || function(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
                t[p] = s[p];
        }
        return t;
    };
    return __assign.apply(this, arguments);
};
import WebSocket from "isomorphic-ws";
import ReconnectingWebSocket from "reconnecting-websocket";
import { v4 as generateUuid } from "uuid";
import { opcodeToTopicMapping } from "./utils";
var heartbeatInterval = 8000;
var apiUrl = "ws://localhost:4001/socket";
var connectionTimeout = 15000;
/**
 * Creates a Connection object
 * @param {Token} token Your token
 * @param {Token} refreshToken Your refresh token
 * @returns {Promise<Connection>} Connection object
 */
export var connect = function (token, refreshToken, _a) {
    var _b = _a.logger, logger = _b === void 0 ? function () { } : _b, _c = _a.onConnectionTaken, onConnectionTaken = _c === void 0 ? function () { } : _c, _d = _a.onClearTokens, onClearTokens = _d === void 0 ? function () { } : _d, _e = _a.url, url = _e === void 0 ? apiUrl : _e, fetchTimeout = _a.fetchTimeout, getAuthOptions = _a.getAuthOptions, waitToReconnect = _a.waitToReconnect;
    return new Promise(function (resolve, reject) {
        var socket = new ReconnectingWebSocket(url, [], {
            connectionTimeout: connectionTimeout,
            WebSocket: WebSocket,
        });
        var apiSend = function (opcode, data, topic, fetchId) {
            if (socket.readyState !== socket.OPEN) {
                return;
            }
            var raw = "{\"operator\":\"".concat(opcode, "\",\"data\":").concat(JSON.stringify(data)).concat(fetchId ? ",\"fetchId\":\"".concat(fetchId, "\"") : "", "}");
            socket.send(JSON.stringify({
                command: opcode,
                destination: topic,
                message: raw
            }));
            logger("out", opcode, data, fetchId, raw);
        };
        var listeners = [];
        socket.addEventListener("close", function (error) {
            // eslint-disable-next-line no-console
            console.log(error);
            if (error.code === 4001) {
                // unauthorized token failed
                socket.close();
                onClearTokens();
            }
            else if (error.code === 4003) {
                // connection taken
                socket.close();
                onConnectionTaken();
            }
            else if (error.code === 4004) {
                // others
                socket.close();
                onClearTokens();
            }
            if (!waitToReconnect)
                reject(error);
        });
        socket.addEventListener("message", function (e) {
            if (e.data === "\"pong\"" || e.data === "pong") {
                logger("in", "pong");
                return;
            }
            var message = JSON.parse(e.data);
            logger("in", message.op, message.d, message.fetchId, e.data);
            if (message.op === "auth-good") {
                var connection_1 = {
                    close: function () { return socket.close(); },
                    once: function (opcode, handler) {
                        var listener = { opcode: opcode, handler: handler };
                        listener.handler = function () {
                            var params = [];
                            for (var _i = 0; _i < arguments.length; _i++) {
                                params[_i] = arguments[_i];
                            }
                            // The params argument is cast to the appropriate type using as Parameters<typeof handler>. 
                            // This ensures that the function is called with the correct number and types of parameters.
                            handler.apply(void 0, params);
                            // remove this actual listener from the array incase it's there already
                            listeners.splice(listeners.indexOf(listener), 1);
                        };
                        // add to arr
                        listeners.push(listener);
                    },
                    addListener: function (opcode, handler) {
                        var listener = { opcode: opcode, handler: handler };
                        listeners.push(listener);
                        return function () { return listeners.splice(listeners.indexOf(listener), 1); };
                    },
                    user: message.d.user,
                    send: apiSend,
                    sendCall: function (opcode, parameters, doneOpcode) {
                        return new Promise(function (resolveCall, rejectFetch) {
                            // this is to avoid ws events queuing up while socket is closed
                            // then it reconnects and fires before auth goes off
                            // and you get logged out
                            if (socket.readyState !== socket.OPEN) {
                                rejectFetch(new Error("websocket not connected"));
                                return;
                            }
                            var ref = !doneOpcode && generateUuid();
                            var timeoutId = null;
                            var unsubscribe = connection_1.addListener(doneOpcode !== null && doneOpcode !== void 0 ? doneOpcode : opcode + ":reply", function (data, arrivedId) {
                                if (!doneOpcode && arrivedId !== ref)
                                    return;
                                if (timeoutId)
                                    clearTimeout(timeoutId);
                                unsubscribe();
                                resolveCall(data);
                            });
                            if (fetchTimeout) {
                                timeoutId = setTimeout(function () {
                                    unsubscribe();
                                    rejectFetch(new Error("timed out"));
                                }, fetchTimeout);
                            }
                            apiSend(opcode, parameters, opcodeToTopicMapping[opcode], ref || undefined);
                        });
                    },
                };
                resolve(connection_1);
            }
            else {
                listeners
                    .filter(function (_a) {
                    var opcode = _a.opcode;
                    return opcode === message.op;
                })
                    .forEach(function (it) {
                    return it.handler(message.d || message.p, message.fetchId || message.ref);
                });
            }
        });
        socket.addEventListener("open", function () {
            var id = setInterval(function () {
                if (socket.readyState === socket.CLOSED) {
                    clearInterval(id);
                }
                else {
                    socket.send("ping");
                    logger("out", "ping");
                }
            }, heartbeatInterval);
            // send auth to get user data to be used to subscribe
            apiSend("auth", __assign({ accessToken: token, refreshToken: refreshToken }, getAuthOptions === null || getAuthOptions === void 0 ? void 0 : getAuthOptions()), opcodeToTopicMapping.auth);
        });
    });
};
