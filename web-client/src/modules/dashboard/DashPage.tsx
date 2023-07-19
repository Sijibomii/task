
interface DashPageProps {}

export const DashPage: React.FC<DashPageProps> = () => {

    return (
        <WaitForWsAndAuth>
            <DefaultDesktopLayout>
                <FeedController />
            </DefaultDesktopLayout>
        </WaitForWsAndAuth>
    )
}