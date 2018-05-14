# Statement for enabling the development environment
DEBUG = True

# Define the application directory
import os
BASE_DIR = os.path.abspath(os.path.dirname(__file__))

SESSION_TYPE = 'memcached'
SECRET_KEY = 'ESIPE'

# Application threads. A common general assumption is
# using 2 per available processor cores - to handle
# incoming requests using one and performing background
# operations using the other.
THREADS_PER_PAGE = 2

# Enable protection agains *Cross-site Request Forgery (CSRF)*
CSRF_ENABLED     = True

#NFC READER
NFC_READER_IP = "127.0.0.1"
NFC_READER_PORT= 5001

#TRANSACTION SERVER
TRANSACTION_SERVER = "http://rss.eight.inside.esiag.info:8080/"



#LOG
LOG_CONF="log.conf"
LOG_FILE="dab-ihm.log"
LOG_ENV="dev"