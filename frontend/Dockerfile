FROM node:alpine

WORKDIR /app

COPY . .

RUN corepack enable && pnpm install

RUN pnpm run build

FROM nginx:alpine

COPY --from=0 /app/dist/biblioteca-frontend/browser /usr/share/nginx/html

EXPOSE 80
