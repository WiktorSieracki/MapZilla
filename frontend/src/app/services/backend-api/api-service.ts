import axios from 'axios';

const baseURL =
  typeof window === 'undefined'
    ? process.env.API_BASE_URL
    : process.env.NEXT_PUBLIC_API_BASE_URL;
export const apiService = axios.create({ baseURL, adapter: 'fetch' });
