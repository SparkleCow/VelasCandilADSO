#!/usr/bin/env bash
# ─────────────────────────────────────────────────────────────
#  Uso:  ./pgcandil.sh            → abre psql interactivo
#        ./pgcandil.sh tablas     → lista todas las tablas
#        ./pgcandil.sh "<query>"  → ejecuta una consulta SQL
# ─────────────────────────────────────────────────────────────

CONTAINER="candil-postgres"
DB="candles"
USER="postgres"
PASS="123456789"


CMD_TABLAS="SELECT table_name FROM information_schema.tables WHERE table_schema = 'public' ORDER BY table_name;"
CMD_VERSION="SELECT version();"

# Verificar que el contenedor esté corriendo
if ! docker ps --format '{{.Names}}' | grep -q "^${CONTAINER}$"; then
  echo "❌  El contenedor '${CONTAINER}' no está corriendo."
  echo "    Arranca el stack con:  docker compose up -d"
  exit 1
fi

# Modo según argumento
case "${1:-}" in

  tablas|tables)
    echo "📋  Tablas en la base '${DB}':"
    docker exec -e PGPASSWORD="${PASS}" "${CONTAINER}" \
      psql -U "${USER}" -d "${DB}" -c "${CMD_TABLAS}"
    ;;

  version)
    docker exec -e PGPASSWORD="${PASS}" "${CONTAINER}" \
      psql -U "${USER}" -d "${DB}" -c "${CMD_VERSION}"
    ;;

  "")
    echo "🕯️  Conectando a '${DB}' en ${CONTAINER}..."
    echo "    Comandos útiles dentro de psql:"
    echo "      \\dt          → listar tablas"
    echo "      \\d <tabla>   → describir tabla"
    echo "      \\l           → listar bases de datos"
    echo "      \\q           → salir"
    echo ""
    docker exec -it -e PGPASSWORD="${PASS}" "${CONTAINER}" \
      psql -U "${USER}" -d "${DB}"
    ;;

  *)
    # Cualquier otra cosa se trata como SQL directo
    echo "⚡  Ejecutando: ${1}"
    docker exec -e PGPASSWORD="${PASS}" "${CONTAINER}" \
      psql -U "${USER}" -d "${DB}" -c "${1}"
    ;;

esac