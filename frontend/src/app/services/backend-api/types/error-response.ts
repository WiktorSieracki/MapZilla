import { FieldValidationError } from '@/app/services/backend-api/types/field-validation-error';

export interface ErrorResponse {
  code: string;
  message: string;
  timestamp: string;
  errors: FieldValidationError[] | null;
  data: null;
}
