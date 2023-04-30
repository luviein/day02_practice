import json

raw_output = '[{"action": "deposit","amount": 20.0,"date": "30-04-2023","time": "23:05:37"},{"action": "withdrawn","amount": 100.0,"date": "30-04-2023","time": "23:05:37"}]'
parsed_output = json.loads(raw_output)

for transaction in parsed_output:
    print("=================")
    print(transaction["action"])
    print(transaction["amount"])
    print(transaction["time"])
    print(transaction["date"])
