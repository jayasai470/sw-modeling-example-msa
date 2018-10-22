```
http localhost:9102/owners ssn=jjy
http localhost:9102/vehicles vehicleId="124" name="bmw" owner="http://localhost:9102/owners/jjy"
http "http://localhost:9102/owners/jjy/vehicles"

```