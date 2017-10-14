
import os

from PIL import Image

def de_jpg(filelist):
	for file in filelist:
		outfile = os.path.splitext(file)[0]+'.jpg'
		if file != outfile:
			try:
				Image.open(file).save(outfile)
			except IOError:
				print('cannot convert',file)


def get_imlist(path):
	return [os.path.join(path,f) for f in os.listdir(path) if f.endswith('.jpg')]




