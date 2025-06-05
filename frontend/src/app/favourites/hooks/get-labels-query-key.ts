const LabelsQueryKey = ['labels'] as const;

export const getLabelsQueryKey = (labelId?: string) => [
  ...LabelsQueryKey,
  ...(labelId ? [labelId] : []),
];
