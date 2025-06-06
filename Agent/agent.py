import psutil
import requests
import schedule
import time
import uuid
import socket
import os
from dotenv import load_dotenv

# Load environment variables from .env file
load_dotenv()

# === CONFIG ===
API_ENDPOINT = os.getenv("API_ENDPOINT", "http://localhost:1010/api/metrics")
TOKEN = os.getenv("TOKEN", "secure_token_optional")
SERVER_ID = os.getenv("SERVER_ID", str(uuid.getnode())[:12])
INTERVAL = int(os.getenv("INTERVAL_SECONDS", 10))

def collect_and_send():
    try:
        cpu = psutil.cpu_percent(interval=1)
        ram = psutil.virtual_memory().percent
        disk = psutil.disk_usage("/").percent
        net = psutil.net_io_counters()

        data = {
            "serverId": SERVER_ID,
            "cpuUsage": cpu,
            "ramUsage": ram,
            "diskUsage": disk,
            "networkSentKB": round(net.bytes_sent / 1024, 2),
            "networkRecvKB": round(net.bytes_recv / 1024, 2)
        }

        headers = {
            "Content-Type": "application/json",
            "Authorization": f"Bearer {TOKEN}"
        }

        response = requests.post(API_ENDPOINT, json=data, headers=headers, timeout=5)

        if response.status_code == 200:
            print(f"[{time.ctime()}] ✅ Sent: CPU {cpu}% | RAM {ram}% | Disk {disk}%")
        else:
            print(f"[{time.ctime()}] ⚠️ Status {response.status_code} | Body: {response.text}")

    except requests.exceptions.RequestException as e:
        print(f"[{time.ctime()}] ❌ Network error: {e}")
    except Exception as e:
        print(f"[{time.ctime()}] ❌ Unexpected error: {e}")

# === RUN ===
schedule.every(INTERVAL).seconds.do(collect_and_send)

print(f"🚀 Agent is running... Sending every {INTERVAL}s to {API_ENDPOINT}")
while True:
    schedule.run_pending()
    time.sleep(1)