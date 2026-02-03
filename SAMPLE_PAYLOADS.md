# Sample API Payloads

## Create Room (POST /api/admin/rooms)

```json
{
  "roomNumber": "101",
  "roomType": "AC",
  "sharingType": 3,
  "floorNumber": 1,
  "bathroomAttached": true,
  "roomRent": 5500.00,
  "roomAdvance": 2000.00
}
```

## Update Room (PUT /api/admin/rooms/{id})

```json
{
  "roomNumber": "101",
  "roomType": "NON_AC",
  "sharingType": 3,
  "floorNumber": 1,
  "bathroomAttached": false,
  "roomRent": 4500.00,
  "roomAdvance": 2000.00
}
```
