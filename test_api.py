
import urllib.request
import json

url = "https://generativelanguage.googleapis.com/v1beta/models?key=AIzaSyC0kApHmAWb9sxiqBqlM9NTEaVgnidrJXw"

try:
    with urllib.request.urlopen(url) as response:
        data = json.loads(response.read().decode('utf-8'))
        found = False
        print("Gemini models found:")
        for model in data.get('models', []):
            if 'gemini' in model['name']:
                print(model['name'])
                found = True
        if not found:
            print("No 'gemini' models found in the list.")
except urllib.error.HTTPError as e:
    print(f"HTTP Error: {e.code}")
except Exception as e:
    print(f"Error: {e}")
