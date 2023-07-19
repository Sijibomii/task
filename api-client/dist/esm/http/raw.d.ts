interface Options {
    baseUrl?: string;
}
type Request = (method: string, endpoint: string, body?: unknown, opts?: Options) => Promise<unknown>;
export type Http = {
    request: Request;
};
export declare const create: (baseOpts: Options) => Http;
export {};
