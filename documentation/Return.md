## İade Oluşturma

### Endpoint
`POST /returns/create/{paymentUuid}`

### Açıklama
Bu endpoint, bir ödeme ile ilişkilendirilmiş bir iade oluşturur.

### İstek Body Parametreleri
- `address` (String, zorunlu): İade adresi.
- `reason` (String, zorunlu): İade nedeni.
- `returnDate` (Date, zorunlu): İade tarihi.
- `userUuid` (String, zorunlu): Kullanıcının UUID'si.
- `productUuid` (String, zorunlu): İade edilen ürünün UUID'si.
- `cartId` (Long, zorunlu): Sepet ID'si.
- `orderId` (Long, zorunlu): Sipariş ID'si.

### Başarılı Yanıt
`201 Created` status code ile iade başarıyla oluşturulduğuna dair mesaj.

### Hata Durumları
- `400 Bad Request`: Eksik veya hatalı istek body parametreleri.
- `404 Not Found`: Belirtilen UUID'ye sahip ödeme, kullanıcı veya ürün bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## İade Güncelleme

### Endpoint
`PUT /returns/update`

### Açıklama
Bu endpoint, bir iadeyi günceller.

### İstek Body Parametreleri
- `address` (String, zorunlu): İade adresi.
- `reason` (String, zorunlu): İade nedeni.
- `returnDate` (Date, zorunlu): İade tarihi.
- `userUuid` (String, zorunlu): Kullanıcının UUID'si.
- `productUuid` (String, zorunlu): İade edilen ürünün UUID'si.
- `cartId` (Long, zorunlu): Sepet ID'si.
- `orderId` (Long, zorunlu): Sipariş ID'si.

### Başarılı Yanıt
`200 OK` status code ile iade başarıyla güncellendiğine dair mesaj.

### Hata Durumları
- `400 Bad Request`: Eksik veya hatalı istek body parametreleri.
- `404 Not Found`: Belirtilen UUID'ye sahip iade bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## İade Bilgilerini Getirme

### Endpoint
`GET /returns/{returnId}`

### Açıklama
Bu endpoint, belirtilen ID'ye sahip bir iadenin bilgilerini getirir.

### Başarılı Yanıt
`200 OK` status code ile belirtilen ID'ye sahip iadenin bilgileri.

### Hata Durumları
- `404 Not Found`: Belirtilen ID'ye sahip iade bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Tüm İadeleri Listeleme

### Endpoint
`GET /returns`

### Açıklama
Bu endpoint, tüm iadelerin listesini getirir.

### Başarılı Yanıt
`200 OK` status code ile tüm iadelerin listesi.

### Hata Durumları
- `500 Internal Server Error`: Sunucu hatası.

## İade Silme

### Endpoint
`DELETE /returns/delete/{returnId}`

### Açıklama
Bu endpoint, belirtilen ID'ye sahip bir iadeyi siler.

### Başarılı Yanıt
`200 OK` status code ile iade başarıyla silindiğine dair mesaj.

### Hata Durumları
- `404 Not Found`: Belirtilen ID'ye sahip iade bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.
