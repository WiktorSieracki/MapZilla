export interface Response<T> {
  code: string;
  message: string;
  timestamp: string;
  errors: null;
  data: T;
}
