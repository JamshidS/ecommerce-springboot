# Promotion Controller API Dokümantasyonu

Bu API dokümantasyonu, Promosyonlarla ilgili HTTP isteklerinin kullanımını ve yanıtını açıklar.

## Promosyon Kaydetme

### Endpoint
`POST /promotions/save`

### Açıklama
Bu endpoint, yeni bir promosyonu kaydeder.

### İstek Body Parametreleri
- `name` (String, zorunlu): Promosyonun adı.
- `description` (String, opsiyonel): Promosyonun açıklaması.
- `discount` (double, zorunlu): Promosyonun indirim miktarı.
- `code` (String, zorunlu): Promosyonun kodu.
- `productUuid` (List<String>, zorunlu): Promosyonun geçerli olduğu ürünlerin UUID'leri.
- `fullName` (String, opsiyonel): Promosyonun tam adı.
- `daysToAdd` (Long, opsiyonel): Promosyonun geçerlilik süresini gün olarak ekler.
- `startDate` (Timestamp, opsiyonel): Promosyonun başlangıç tarihi.
- `endDate` (Timestamp, opsiyonel): Promosyonun bitiş tarihi.

### Başarılı Yanıt
`201 Created` status code ile kaydedilen promosyonun UUID'si.

### Hata Durumları
- `400 Bad Request`: Eksik veya hatalı istek body parametreleri.
- `500 Internal Server Error`: Sunucu hatası.

## Promosyon Güncelleme

### Endpoint
`PUT /promotions/update`

### Açıklama
Bu endpoint, varolan bir promosyonu günceller.

### İstek Body Parametreleri
(Tüm parametrelerin Promosyon Kaydetme ile aynı olduğunu varsayalım)

### Başarılı Yanıt
`200 OK` status code ile promosyonun güncellendiğine dair mesaj.

### Hata Durumları
- `400 Bad Request`: Eksik veya hatalı istek body parametreleri.
- `404 Not Found`: Güncellenecek promosyon bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Promosyon Silme

### Endpoint
`DELETE /promotions/delete/{promotionUuid}`

### Açıklama
Bu endpoint, belirtilen promosyonu siler.

### Başarılı Yanıt
`200 OK` status code ile promosyonun başarıyla silindiğine dair mesaj.

### Hata Durumları
- `404 Not Found`: Silinecek promosyon bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Promosyonun Geçerliliğini Kontrol Etme

### Endpoint
`GET /promotions/validate/{promotionUuid}`

### Açıklama
Bu endpoint, belirtilen promosyonun geçerli olup olmadığını kontrol eder.

### Başarılı Yanıt
`200 OK` status code ile promosyonun geçerli olup olmadığına dair boolean bir değer.

### Hata Durumları
- `404 Not Found`: Kontrol edilecek promosyon bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Tüm Promosyonları Listeleme

### Endpoint
`GET /promotions/all`

### Açıklama
Bu endpoint, sistemdeki tüm promosyonları listeler.

### Başarılı Yanıt
`200 OK` status code ile tüm promosyonların listesi.

### Hata Durumları
- `500 Internal Server Error`: Sunucu hatası.

## UUID ile Promosyon Getirme

### Endpoint
`GET /promotions/{promotionUuid}`

### Açıklama
Bu endpoint, belirtilen UUID'ye sahip promosyonu getirir.

### Başarılı Yanıt
`200 OK` status code ile belirtilen promosyonun detayları.

### Hata Durumları
- `404 Not Found`: Belirtilen UUID'ye sahip promosyon bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

## Ürün UUID'sine Göre Promosyonları Listeleme

### Endpoint
`GET /promotions/product/{productUuid}`

### Açıklama
Bu endpoint, belirtilen ürün UUID'sine sahip ürünler için geçerli olan tüm promosyonları listeler.

### Başarılı Yanıt
`200 OK` status code ile belirtilen ürün için geçerli promosyonların listesi.

### Hata Durumları
- `404 Not Found`: Belirtilen UUID'ye sahip ürün bulunamadı.
- `500 Internal Server Error`: Sunucu hatası.

