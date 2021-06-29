from urllib.parse import urlencode
from urllib.request import Request, urlopen
import json
import base64


with open('users.txt', 'r') as file:
	lines = file.read().splitlines()

	url = "http://localhost:9091/invictus/v1/users"
	headers = {
    "Content-Type": "application/json",
    "Accept": "application/json",
	}

	for line in lines:
		#print(line)
		items = line.split(',')
		items2 = line.split('}')[1].split(',')[1:]
		#print(items2)
		data = {
			"firstName": items[0].strip(),
			"secondName": items[1].strip(),
			"gender": items[4].strip(),
			"resources": [items2[5].strip()],
			"username": items[2].strip(),
			"password": items[2].strip(),
			"languages": [x.strip() for x in line.strip().split('{')[1].split('}')[0].split(',')],
			"location": items2[0].strip() + ", " + items2[1].strip(),
			"typeOfSeeker": items2[3].strip(),
			"typeOfIllness": items2[2].strip(),
			"anonymous": True if items2[4].strip() == "True" else False,
			"age": int(items[3].strip()),
		}

		print(json.dumps(data).encode("utf-8"))
		req = Request(url, data=json.dumps(data).encode("utf-8"), headers=headers)
		res = urlopen(req).read().decode()
		#print(res)
		



with open('groups.txt', 'r') as file:
	lines = file.read().splitlines()

	url = "http://localhost:9091/invictus/v1/groups"
	headers = {
    "Content-Type": "application/json",
    "Accept": "application/json",
	"Authorization": "Basic YWRtaW46YWRtaW4="
	}

	for line in lines:
		print(line)
		items = line.split(',')
		items2 = line.split('}')[1].split(',')[1:]
		data = {
			"groupName": items[0].strip(),
			"resources": [items2[3].strip()],
			"languages": [x.strip() for x in line.strip().split('{')[1].split('}')[0].split(',')],
			"location": items2[0].strip() + ", " + items2[1].strip(),
			"typeOfIllness": items2[2].strip(),
		}
		#print(headers)
		print(json.dumps(data).encode("utf-8"))
		req = Request(url, data=json.dumps(data).encode("utf-8"), headers=headers)
		res = urlopen(req).read().decode()
		print(res)

