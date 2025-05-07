// @ts-check

import { includeIgnoreFile } from '@eslint/compat';
import { FlatCompat } from '@eslint/eslintrc';
import eslint from '@eslint/js';
import vitest from '@vitest/eslint-plugin';
import jestDom from 'eslint-plugin-jest-dom';
import noRelativeImportPaths from 'eslint-plugin-no-relative-import-paths';
import testingLibrary from 'eslint-plugin-testing-library';
import { dirname, resolve } from 'path';
import tseslint from 'typescript-eslint';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const compat = new FlatCompat({
  baseDirectory: __dirname,
});
const gitignorePath = resolve(__dirname, '.gitignore');

const eslintConfig = tseslint.config(
  includeIgnoreFile(gitignorePath),
  eslint.configs.recommended,
  tseslint.configs.strict,
  tseslint.configs.stylistic,
  testingLibrary.configs['flat/react'],
  jestDom.configs['flat/recommended'],
  compat.extends(
    'next/core-web-vitals',
    'plugin:jsx-a11y/strict'
  ),
  {
    plugins: {
      'no-relative-import-paths': noRelativeImportPaths,
    },
    rules: {
      'no-relative-import-paths/no-relative-import-paths': [
        'error',
        { rootDir: 'src', prefix: '@', allowSameFolder: false },
      ],
    },
  },
  {
    files: ['tests/**'],
    plugins: {
      vitest,
    },
    rules: {
      ...vitest.configs?.recommended.rules,
    },
  },
  compat.extends('prettier')
);

export default eslintConfig;
