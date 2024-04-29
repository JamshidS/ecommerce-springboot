## Kargo Oluşturma

### Endpoint
`POST /shippings/create`

### Açıklama
Bu endpoint, bir kargo oluşturur.

### İstek Body Parametreleri
- `address` (String, zorunlu): Kargo adresi.
- `shippedAt` (Date, isteğe bağlı): Kargonun gönderildiği tarih.
- `senderUuid` (String, zorunlu): Gönderenin UUID'si.
- `orderUuid` (String, zorunlu): Kargonun ilişkilendirildiği siparişin UUID'si.

### Başarılı Yanıt
`201 Created` status code ile kargo başarıyla oluşturulduğuna dair mesaj.

### Hata Durumları
- `400 Bad Request`: Eksik veya hatalı istek body parametreleri.
- `500 Internal Server Error`: Sunucu hatası.

## Kargo Güncelleme

### Endpoint
`PUT /shippings/update/{id}`

### Açıklama
Bu endpoint, belirtilen bir kargonun bilgilerini günceller.

### Path Parametresi
- `id` (Long, zorunlu): Güncellenmek istenen kargonun ID'si.

### İstek Body Parametreleri
- `address` (String, zorunlu): Kargo adresi.
- `shippedAt` (Date, isteğe bağlı): Kargonun gönderildiği tarih.
- `senderUuid` (String, zorunlu): Gönderenin UUID'si.
- `orderUuid` (String, zorunlu): Kargonun ilişkilendirildiği siparişin UUID'si.

### Başarılı Yanıt
`200 OK` status code ile kargo başarıyla güncellendiğine dair mesaj.

### Hata Durumları
- `400 Bad Request`: Eksik veya hatalı istek body parametreleri.
- `404 Not Found`: Belirtilen ID'ye sahip kargo bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Kargo Silme

### Endpoint
`DELETE /shippings/delete/{id}`

### Açıklama
Bu endpoint, belirtilen bir kargoyu siler.

### Path Parametresi
- `id` (Long, zorunlu): Silinmek istenen kargonun ID'si.

### Başarılı Yanıt
`200 OK` status code ile kargo başarıyla silindiğine dair mesaj.

### Hata Durumları
- `404 Not Found`: Belirtilen ID'ye sahip kargo bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Kargo Bilgilerini Getirme

### Endpoint
`GET /shippings/{id}`

### Açıklama
Bu endpoint, belirtilen bir kargonun bilgilerini getirir.

### Path Parametresi
- `id` (Long, zorunlu): Getirilmek istenen kargonun ID'si.

### Başarılı Yanıt
`200 OK` status code ile belirtilen kargonun bilgileri.

### Hata Durumları
- `404 Not Found`: Belirtilen ID'ye sahip kargo bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Tüm Kargoları Listeleme

### Endpoint
`GET /shippings/all`

### Açıklama
Bu endpoint, tüm kargoların listesini getirir.

### Başarılı Yanıt
`200 OK` status code ile tüm kargoların listesi.

### Hata Durumları
- `404 Not Found`: Kargo bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.
