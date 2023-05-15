import { http } from "@sijibomi/api-client";
import { useHttpClient as useHttp } from "../global-stores/useHttpClient";

// return the http client
export const useHttpClient = () => {
    const [setHttp, httpClient] = useHttp((state) => [state.setHttpClient, state.http]);

    if(httpClient === null){
        const { create } = http;
        const client = create(process.env.BASE_API_URL)
        setHttp(create);
        return client
    }
    
    return httpClient;
};