#!/usr/bin/env bash
# Chạy backend (DATN) ở chế độ dev để dùng song song với Frontend (project khác).
# Cách dùng:
#   ./run-backend-dev.sh
# Sau đó mở terminal khác, vào project FE và chạy (npm start / ng serve / ...).

set -e
cd "$(dirname "$0")"

if [ -f ./env.local.sh ]; then
  echo "Loading env from env.local.sh..."
  source ./env.local.sh
fi

export SPRING_PROFILES_ACTIVE="${SPRING_PROFILES_ACTIVE:-dev}"
echo "Starting backend (profile=$SPRING_PROFILES_ACTIVE, port=${SERVER_PORT:-9091})..."
echo "Frontend có thể gọi API tại: http://localhost:${SERVER_PORT:-9091}/api"
echo ""

./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=$SPRING_PROFILES_ACTIVE"
