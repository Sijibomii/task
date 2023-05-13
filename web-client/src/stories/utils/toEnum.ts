export const toEnum = <T>(arr: T[]) => ({
    control: {
      type: 'radio'
    },
    options: arr,
  });
  