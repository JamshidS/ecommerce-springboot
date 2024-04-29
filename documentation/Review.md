## İnceleme Oluşturma

### Endpoint
`POST /reviews/save`

### Açıklama
Bu endpoint, bir inceleme oluşturur.

### İstek Body Parametreleri
- `rating` (short, zorunlu): İncelemenin puanı.
- `comment` (String, isteğe bağlı): İnceleme metni.
- `userUuid` (String, zorunlu): Kullanıcının UUID'si.
- `productUuid` (String, zorunlu): İncelenen ürünün UUID'si.

### Başarılı Yanıt
`201 Created` status code ile inceleme başarıyla oluşturulduğuna dair mesaj.

### Hata Durumları
- `400 Bad Request`: Eksik veya hatalı istek body parametreleri.
- `500 Internal Server Error`: Sunucu hatası.

## Kullanıcı ve Ürüne Göre İncelemeyi Getirme

### Endpoint
`GET /reviews`

### Açıklama
Bu endpoint, belirtilen kullanıcı ve ürüne göre bir incelemeyi getirir.

### Query Parametreleri
- `userUuid` (String, zorunlu): Kullanıcının UUID'si.
- `productUuid` (String, zorunlu): İncelenen ürünün UUID'si.

### Başarılı Yanıt
`200 OK` status code ile belirtilen kullanıcı ve ürüne göre inceleme.

### Hata Durumları
- `404 Not Found`: Belirtilen kullanıcı ve ürüne göre inceleme bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Ürüne Göre İncelemeleri Listeleme

### Endpoint
`GET /reviews/product`

### Açıklama
Bu endpoint, belirtilen ürüne göre tüm incelemeleri listeler.

### Query Parametresi
- `productUuid` (String, zorunlu): İncelenen ürünün UUID'si.

### Başarılı Yanıt
`200 OK` status code ile belirtilen ürüne göre tüm incelemelerin listesi.

### Hata Durumları
- `404 Not Found`: Belirtilen ürüne göre inceleme bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Kullanıcı ve Ürüne Göre İncelemeyi Güncelleme

### Endpoint
`PUT /reviews/update`

### Açıklama
Bu endpoint, belirtilen kullanıcı ve ürüne göre bir incelemeyi günceller.

### Query Parametreleri
- `userUuid` (String, zorunlu): Kullanıcının UUID'si.
- `productUuid` (String, zorunlu): İncelenen ürünün UUID'si.

### İstek Body Parametreleri
- `rating` (short, zorunlu): İncelemenin puanı.
- `comment` (String, isteğe bağlı): İnceleme metni.

### Başarılı Yanıt
`200 OK` status code ile inceleme başarıyla güncellendiğine dair mesaj.

### Hata Durumları
- `400 Bad Request`: Eksik veya hatalı istek body parametreleri.
- `404 Not Found`: Belirtilen kullanıcı ve ürüne göre inceleme bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Kullanıcı ve Ürüne Göre İncelemeyi Silme

### Endpoint
`DELETE /reviews/delete`

### Açıklama
Bu endpoint, belirtilen kullanıcı ve ürüne göre bir incelemeyi siler.

### Query Parametreleri
- `userUuid` (String, zorunlu): Kullanıcının UUID'si.
- `productUuid` (String, zorunlu): İncelenen ürünün UUID'si.

### Başarılı Yanıt
`200 OK` status code ile inceleme başarıyla silindiğine dair mesaj.

### Hata Durumları
- `404 Not Found`: Belirtilen kullanıcı ve ürüne göre inceleme bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## İnceleme UUID'sine Göre İncelemeleri Silme

### Endpoint
`DELETE /reviews/delete/{reviewUuid}`

### Açıklama
Bu endpoint, belirtilen inceleme UUID'sine sahip tüm incelemeleri siler.

### Path Parametresi
- `reviewUuid` (String, zorunlu): İnceleme UUID'si.

### Başarılı Yanıt
`200 OK` status code ile belirtilen inceleme UUID'sine sahip tüm incelemeler silindiğine dair mesaj.

### Hata Durumları
- `404 Not Found`: Belirtilen inceleme UUID'sine sahip inceleme bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Onaylanmış İncelemeleri Ürüne Göre Listeleme

### Endpoint
`GET /reviews/approved`

### Açıklama
Bu endpoint, belirtilen ürüne ait tüm onaylanmış incelemeleri listeler.

### Query Parametresi
- `productUuid` (String, zorunlu): İncelenen ürünün UUID'si.

### Başarılı Yanıt
`200 OK` status code ile belirtilen ürüne ait tüm onaylanmış incelemelerin listesi.

### Hata Durumları
- `404 Not Found`: Belirtilen ürüne ait onaylanmış inceleme bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## İncelemeyi Onaylama

### Endpoint
`PUT /reviews/approve`

### Açıklama
Bu endpoint, belirtilen incelemeyi onaylar.

### Query Parametresi
- `reviewUuid` (String, zorunlu): İncelenen ürünün UUID'si.

### Başarılı Yanıt
`200 OK` status code ile inceleme başarıyla onaylandı.

### Hata Durumları
- `404 Not Found`: Belirtilen inceleme UUID'sine sahip inceleme bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.
