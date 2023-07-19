"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.opcodeToTopicMapping = exports.AUTH_TOPIC = exports.messages = void 0;
// just a log messages op possible by the ws client
exports.messages = ["auth", "auth-good", ""];
exports.AUTH_TOPIC = '/topic/user/auth';
exports.opcodeToTopicMapping = {
    "auth": exports.AUTH_TOPIC,
};
