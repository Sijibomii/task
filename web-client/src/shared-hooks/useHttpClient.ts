import { http } from "@sijibomi/task-api-client";
import { useHttpClient as useHttp } from "../global-stores/useHttpClient";

// return the http client
export const useHttpClient = () => {
    const [setHttp, httpClient] = useHttp((state) => [state.setHttpClient, state.http]);

    if(httpClient === null){
        const { create } = http;
        const client = create({
            baseUrl: process.env.BASE_API_URL || 'http://localhost:8080'
        })
        setHttp(client);
        return client
    }
    
    return httpClient;
};