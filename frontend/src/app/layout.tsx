import { Navbar } from '@/app/core/navbar';
import '@/app/globals.css';
import SessionProviderWrapper from '@/app/homepage/context/SessionProviderWrapper';
import TanstackProvider from '@/components/providers/tanstack-provider';
import { BackgroundIcon } from '@/components/ui/background-icon';
import type { Metadata } from 'next';
import { Geist, Geist_Mono } from 'next/font/google';
// import { AuthProvider } from "./context/AuthProvider";

const geistSans = Geist({
  variable: '--font-geist-sans',
  subsets: ['latin'],
});

const geistMono = Geist_Mono({
  variable: '--font-geist-mono',
  subsets: ['latin'],
});

export const metadata: Metadata = {
  title: 'Mapzilla',
  description: 'App for 15-minute city',
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`${geistSans.variable} ${geistMono.variable} relative antialiased`}>
        <BackgroundIcon className="fixed inset-0 -z-10 h-full w-full" />
        <SessionProviderWrapper>
          <TanstackProvider>
            <Navbar />
            {children}
          </TanstackProvider>
        </SessionProviderWrapper>
      </body>
    </html>
  );
}
