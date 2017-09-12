#!/usr/bin/python
doc_head = '\
<?xml version="1.0" encoding="UTF-8"?>\
<kml xmlns="http://www.opengis.net/kml/2.2">\
<Document>'

doc_end = '\
</Document>\
</kml>'

content = '<Placemark><name>{0}</name><Point><coordinates>\
{1},{2}</coordinates></Point></Placemark>'
