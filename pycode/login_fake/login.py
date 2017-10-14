import requests

res = requests.get(url='http://124.114.203.117:6001/studentlogin.aspx')
with open('NCRE报名系统.html','wb') as fp:
    fp.write(res.content)
