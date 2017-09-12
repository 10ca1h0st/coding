#!/usr/bin/python

import sys

import logging
logging.getLogger('scapy').setLevel(1)

import pygeoip
from scapy.all import *

from KML import doc_head,content,doc_end

geoip_data = pygeoip.GeoIP('/root/Downloads/GeoLiteCity.dat')
def getLocation(ip):
    try:
        rec = geoip_data.record_by_name(ip)
        city = rec['city']
        country = rec['country_name']
        if(city==''):
            location = country
        else:
            location = country+','+city
        return location
    except Exception,e:
        return 'No Record'

def getPackets(in_file=None,verbose=None):
    if in_file != None:
        packets = rdpcap(in_file)
        packets = packets.filter(lambda x:'TCP' in x)
        if verbose:
            for pkt in packets:
                print2(pkt)
    else:
        packets = sniff(filter='tcp port 80',prn=print2 if verbose else None,count=100)
        wrpcap('/root/Downloads/one.pcap',packets)
    return packets


def print2(pkt):
    src = getLocation(pkt['IP'].src)
    dst = getLocation(pkt['IP'].dst)
    print '[+] Src:'+pkt['IP'].src+' --> Dst:'+pkt['IP'].dst
    print '    Src:'+src+' --> Dst:'+dst


def getGPS(ip):
    try:
        rec = geoip_data.record_by_name(ip)
        city = rec['city']
        longitude = rec['longitude']
        latitude = rec['latitude']
        return city,longitude,latitude
    except:
        return "xi'an",'108.9286','34.2583'


def getKML(pkts):
    kml = doc_head
    for pkt in pkts:
        ip = pkt['IP'].src
        if ip == '192.168.1.103' or ip == '192.168.0.103':
            continue
        city,longitude,latitude = getGPS(ip)
        kml += content.format(ip+'('+city+')',longitude,latitude)

    kml += doc_end
    print kml


def main(verbose=None):
    if len(sys.argv)>1:
        pkts = getPackets(sys.argv[1])
    else:
        pkts = getPackets()
    if verbose:
        getKML(pkts)



if __name__ == '__main__':
    main(True)

