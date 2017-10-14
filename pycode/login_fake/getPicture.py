#!/usr/bin/python

import codecs
import requests
#import urllib.request

cookies={'ASP.NET_SessionId':'2wv5objbbctbkr5skn0otv0c','BIGipServerhw_passport_pool':'386582538.20480.0000',
    'Hm_lpvt_187bdd36688721ea2d9f12a3fe912e0c':'1505286004',
    'Hm_lvt_187bdd36688721ea2d9f12a3fe912e0c':'1505285237,1505285245',
    '__qc_wId':'192',
    'pgv_pvid':'9679021500'}
    
headers={'Host':'checkimage.etest.net.cn',
    'User-Agent':'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0',
    'Accept': '*/*',
    'Accept-Language':'en-US,en;q=0.8,zh-CN;q=0.5,zh;q=0.3',
    'Accept-Encoding':'gzip, deflate, br',
    'Referer':'https://passport.etest.net.cn/LoginIframe.aspx?redirectUrl=http://124.114.203.117:6001/VerifyTicket.aspx&ReturnUrl=http://ncre.etest.net.cn/VerifyPassport.aspx?LoginType=0',
    'Connection':'keep-alive',
    'Pragma':'no-cache',
    'Cache-Control':'no-cache'}
   
    
def test(url):
    res = urllib.request.urlopen(url)
    with open('pictures/1.jpg','wb') as fp:
        fp.write(res.read())

def getPicture(i):
    res1 = requests.post(url='https://passport.etest.net.cn/CheckImage/LoadCheckImage',cookies=cookies)
    text = res1.text[1:-1]
    res2 = requests.get(url=text,headers=headers)
    with open('pictures/'+str(i)+'.jpg','wb') as fp:
        fp.write(res2.content)
        
        
        
if __name__ == '__main__':
	'''for i in range(200):
		getPicture(i)'''
	print('ok')
