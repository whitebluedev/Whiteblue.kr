/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  swcMinify: true,
  env: {
    LOGIN_URL: process.env.LOGIN_URL,
  },
}

module.exports = {
  devIndicators: {
    buildActivity: false,
  },
};