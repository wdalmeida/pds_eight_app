from setuptools import setup
#python setup.py install
#python app.py
setup(name='dab_nfc_reader',
      version='0.1',
      description='Read NFC card when put on device and send socket to GUI/IHM',
      author='Warren D\'ALMEIDA',
      author_email='warren.dalmeida@etu.u-pec.fr',
      packages=['dab'],
      install_requires=[
          'nxppy'
      ],
      zip_safe=False)