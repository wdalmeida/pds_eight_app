from setuptools import setup,find_packages
#python setup.py install
#python app.py
setup(name='DAB-IHM',
      version='0.1',
      description='DAB - IHM',
      author='Warren D\'ALMEIDA',
      author_email='warren.dalmeida@etu.u-pec.fr',
      packages=find_packages(),
      install_requires=[
          'flask',
          'flask-socketio'
      ],
      zip_safe=False)