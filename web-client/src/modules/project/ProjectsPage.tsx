import CheckSquare from "@/icons/CheckSquare";
import SvgSolidGitHub from "@/icons/GitHub";
import SvgSolidGoogle from "@/icons/SolidGoogle";
import { useTypeSafeHttp } from "@/shared-hooks/useTypeSafeHttp";
import { Button } from "@/ui/Button";
import { useRouter } from "next/router";
import { useCallback } from "react";
import { isServer } from "../../lib/isServer";
import { useTokenStore } from "../auth/useTokenStore";
import { useVerifyLoggedIn } from "../auth/useVerifyLoggedIn";

interface ProjectButtonProps {
    children: React.ReactNode | [React.ReactNode, React.ReactNode];
    dev?: true;
    className?: string;
    type?: "button" | "submit" | "reset" | undefined;
    loading?: boolean;
    projectId: string;
}

export const ProjectButton: React.FC<ProjectButtonProps> = ({
    children,
    dev,
    type, 
    loading,
    projectId,
    ...props
  }) => {
    const { push } = useRouter();
   
    const clickHandler = useCallback(() => {
        push(`/dashboard?projectId=${projectId}`)
    }, [push, projectId]);
  
    return (
      <Button
        className="justify-center text-base py-3 mt-2 bg-black"
        color={dev ? "primary" : "secondary"}
        type={type ? type : undefined}
        loading={loading ? true: false}
        onClick={clickHandler}
        {...props}
      >
        <div
          className={Array.isArray(children) ? "grid gap-4": ""}
          style={{
            gridTemplateColumns: "1fr auto 1fr",
          }}
        >
          {Array.isArray(children) ? [ ...children] : children}
          <div />
        </div>
      </Button>
    );
  };



// projects page
export const ProjectsPage: React.FC = () => {
    const { push } = useRouter();
    if (!useVerifyLoggedIn() && !isServer) {
        push("/");
    }
    const accessToken = useTokenStore((s) => s.accessToken);

    const { isLoading, data } = useTypeSafeHttp("allProjects", {
        staleTime: Infinity,
        enabled: !isServer,
        refetchOnMount: "always"
      }, [accessToken as string]);
    
    return (
        <div className="flex items-center justify-center w-screen h-screen">
        <div className="flex items-center justify-center">
          <div className="hidden sm:flex" />
          <div className="flex  m-auto flex-col p-14 gap-5 bg-[#151a21] sm:rounded-8 z-10 sm:w-400 w-full">
            <div className="flex gap-2 flex-col text-center">
              <span className="text-3xl text-[#dee3ea] font-bold">Select a project</span>
            </div>
            <div className="flex flex-col gap-4">
                {data && data.data?.map((project) => (
                    <ProjectButton  projectId={project.board_id} key={project.id}>
                        
                    <CheckSquare width={20} height={20} />
                     {project.label}
                </ProjectButton>
                ))}
              
             
            </div> 
          </div>
          <div className="flex flex-row absolute bottom-0 w-full justify-between px-5 py-5 mt-auto items-center sm:px-7">
            <div className="hidden sm:flex">
              {/* <LgLogo /> */}
            </div>
            <div className="flex flex-row gap-6 text-[#5d7290]">
              <a href="mail:to" className="hover:text-[#b2bdcd]">
                sijibomiolajubu@gmail.com
              </a>
              <a
                href="https://github.com/sijibomii/task/issues"
                className="ml-2 hover:text-[#b2bdcd]"
              >
                Report a bug
              </a>
              <div className="flex flex-row gap-6 sm:gap-4">
                <a
                  href="https://github.com/sijibomii/task"
                  target="_blank"
                  rel="noreferrer"
                >
                  <SvgSolidGitHub
                    width={20}
                    height={20}
                    className="ml-2 cursor-pointer hover:text-[#b2bdcd]"
                  />
                </a>
              </div>
            </div>
          </div>
        </div>
        </div>
    )
};