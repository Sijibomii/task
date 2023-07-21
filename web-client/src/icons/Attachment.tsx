import * as React from "react";

interface AttachmentProps {
    width?: number
    height?: number
    className?: string
}

const Attachment: React.FC<AttachmentProps> = ({
    width= 16, height=16, className
}) => {
    
    return (
        <svg xmlns="http://www.w3.org/2000/svg" width={`${width}`} height={`${height}`} className={`${className}`} viewBox="0 0 100 100" id="attachment"><path d="M18.8 85.1c-7.8-7.8-7.8-20.5 0-28.3L63.1 13c5.5-5.5 14.3-5.5 19.8 0s5.5 14.3 0 19.8L38.6 76.7c-3.1 3.1-8.2 3.1-11.3 0-3.1-3.1-3.1-8.2 0-11.3l22.3-21.8c.8-.8 2.1-.8 2.8 0 .8.8.8 2.1 0 2.8L30.2 68.2c-1.5 1.5-1.5 4.1 0 5.6 1.6 1.6 4.1 1.6 5.7 0L80.2 30c3.9-3.9 3.9-10.2 0-14.1-3.9-3.9-10.2-3.9-14.1 0L21.7 59.7c-6.2 6.2-6.2 16.4 0 22.6 6.3 6.2 16.4 6.2 22.6 0l38.3-37.8c.8-.8 2.1-.8 2.8 0 .8.8.8 2.1 0 2.8L47.1 85.2c-7.8 7.7-20.4 7.8-28.3-.1z"></path><path fill="#00F" d="M664-510v1684h-1784V-510H664m8-8h-1800v1700H672V-518z"></path></svg>
    );
  }
export default Attachment;