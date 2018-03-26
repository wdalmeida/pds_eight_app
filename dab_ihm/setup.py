from setuptools import setup
#python setup.py install
#python app.py
setup(name='DAB-IHM',
      version='0.1',
      description='DAB - IHM',
      author='Warren D\'ALMEIDA',
      author_email='warren.dalmeida@etu.u-pec.fr',
      packages=['dab'],
      install_requires=[
          'flask',
          'sqlalchemy',
          'flask-socketio'
      ],
      zip_safe=False)