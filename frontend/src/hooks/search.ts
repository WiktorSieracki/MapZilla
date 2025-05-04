export const searchLocation = async (query: string) => {
  if (!query) return null;
  const response = await fetch(
    `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(query)}`
  );
  return response.json();
};
