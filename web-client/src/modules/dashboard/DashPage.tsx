import { WaitForWsAndAuth } from "../auth/WaitForWsAndAuth"
import { DefaultDesktopLayout } from "../layouts/DefaultDesktopLayout"
import { ChannelController } from "./ChannelController"
import { TaskController } from "./TaskController"
import { useDashRouter } from "../../global-stores/useDashRouter"
import { ReactElement } from "react";

interface DashPageProps {}

export const DashPage: React.FC<DashPageProps> = () => {

    const dashRouter = useDashRouter();

    interface RouterMap {
        [key: string]: ReactElement;
    }

    const routerMap: RouterMap = {
        task: <TaskController />,
        channel: <ChannelController />
    }

    return (
        <>
        <WaitForWsAndAuth>
            <DefaultDesktopLayout>
                {routerMap[dashRouter.current]}
            </DefaultDesktopLayout>
        </WaitForWsAndAuth> 
        </>
    )
}
{/*  */}