const FavouriteQueryKey = ['favourite'] as const;

export const getFavouriteQueryKey = (favouriteId?: string) => [
  ...FavouriteQueryKey,
  ...(favouriteId ? [favouriteId] : []),
];
