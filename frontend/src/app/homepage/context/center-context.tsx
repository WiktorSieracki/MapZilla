'use client';

import { CenterContextProps } from '@/app/homepage/interface/center-interface';
import { createContext, ReactNode, useContext, useState } from 'react';
import { OverpassApiResponse } from '@/app/homepage/interface/api-response';

const CenterContext = createContext<CenterContextProps | undefined>(undefined);

export const CenterContextProvider = ({
  children,
}: {
  children: ReactNode;
}) => {
  const [center, setCenter] = useState<{ x: number; y: number }>({
    x: 54.39482637467512,
    y: 18.574318885803226,
  });
  const [data, setData] = useState<OverpassApiResponse | null>(null);
  const [locationCenter, setLocationCenter] = useState({ x: 0, y: 0 });

  return (
    <CenterContext.Provider
      value={{
        center,
        setCenter,
        data,
        setData,
        locationCenter,
        setLocationCenter,
      }}>
      {children}
    </CenterContext.Provider>
  );
};

export const useCenterContext = () => {
  const context = useContext(CenterContext);
  if (!context) {
    throw new Error(
      'useCenterContext must be used within a CenterContextProvider'
    );
  }
  return context;
};
