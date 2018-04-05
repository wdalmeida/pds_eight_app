# Statement for enabling the development environment
DEBUG = True

# Define the application directory
import os
BASE_DIR = os.path.abspath(os.path.dirname(__file__))

# Application threads. A common general assumption is
# using 2 per available processor cores - to handle
# incoming requests using one and performing background
# operations using the other.
THREADS_PER_PAGE = 2

# Enable protection agains *Cross-site Request Forgery (CSRF)*
CSRF_ENABLED     = True

#DBserver
DB_SERVER = "postgresql://postgres:50USme229@localhost:5432/pds_test"
DB_SERVER_PORT = 5001
DB_NAME= 5001
DB_USER = 5001
DB_PASSWORD= 5001


#LOG
LOG_CONF="log.conf"
LOG_FILE="dab-ihm.log"
LOG_ENV="dev"