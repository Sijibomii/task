// just a log messages op possible by the ws client
export const messages = ["auth", "auth-good", ""];

export const AUTH_TOPIC = '/topic/user/auth';

export const opcodeToTopicMapping : { [key: string]: string } = {
  "auth": AUTH_TOPIC,
};
