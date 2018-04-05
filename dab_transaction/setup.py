from setuptools import setup,find_packages
#python setup.py install
#python app.py
setup(name='dab-transaction-service',
      version='0.1',
      description='server rest - call by <<dab>> to create and confirm traansaction or identity',
      author='Warren D\'ALMEIDA',
      author_email='warren.dalmeida@etu.u-pec.fr',
      packages=find_packages(),
      install_requires=[
          'flask',
          'sqlalchemy',
          'psycopg2',
          'kafka'
      ],
      zip_safe=False)