import '../styles/global.css';
import { AppProps } from "next/app";
import { QueryClientProvider } from "react-query";
import Head from "next/head";
import { queryClient } from "../lib/queryClient"; 
import { ErrorToastController } from "../modules/errors/ErrorToastController";


export default function App({ Component, pageProps }: AppProps) {
  return (
    <QueryClientProvider client={queryClient}> 
        <Head>
            <link rel="icon" href="/favicon.ico" type="image/x-icon" />
            <link rel="manifest" href="/manifest.json" />
            <meta
              name="viewport"
              content="width=device-width, initial-scale=1, user-scalable=no, user-scalable=0"
            />
            <link rel="apple-touch-icon" href=""></link>
            <link rel="apple-touch-startup-image" href="" />
          </Head>
        <Component {...pageProps} />
        <ErrorToastController />
    </QueryClientProvider>
  )
  
}