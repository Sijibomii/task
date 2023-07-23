import { WaitForWsAndAuth } from "../auth/WaitForWsAndAuth"
import { DefaultDesktopLayout } from "../layouts/DefaultDesktopLayout"
import { TaskController } from "./TaskController"

interface DashPageProps {}

export const DashPage: React.FC<DashPageProps> = () => {

    return (
        <>
        <WaitForWsAndAuth>
            <DefaultDesktopLayout>
                {/* if task is choosen */}
                {/* <TaskController /> */}
                

            </DefaultDesktopLayout>
        </WaitForWsAndAuth> 
        </>
    )
}
{/*  */}