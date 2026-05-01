# 🧠 FluentRevise — Math Quiz Overlay

App Android **chạy ngầm**, cứ mỗi **5 phút** sẽ hiện popup toán học **đè lên toàn màn hình** (kể cả khi đang dùng Facebook, YouTube, v.v.) – người dùng phải nhập đúng đáp án mới tắt được.

---

## ✨ Tính năng

| Tính năng | Chi tiết |
|---|---|
| ⏱ Tự động popup | Mỗi 5 phút hiện ô nhập toán |
| 📱 Đè lên mọi app | Dùng `TYPE_APPLICATION_OVERLAY` |
| 🔢 4 loại toán | Cộng, Trừ, Nhân, Chia ngẫu nhiên |
| 🔒 Bắt buộc trả lời đúng | Không tắt được overlay nếu sai |
| ❌ Hiệu ứng shake khi sai | Ô nhập rung khi nhập sai |
| 🔄 Auto-restart | `START_STICKY` + AlarmManager khi bị kill |
| 📴 Khởi động cùng máy | `BootReceiver` sau khi reboot |
| 🌙 Thức màn hình | `SCREEN_BRIGHT_WAKE_LOCK` khi hiện popup |

---

## 📁 Cấu trúc project

```
FluentRevise/
├── app/src/main/
│   ├── AndroidManifest.xml          # Permissions + Service/Receiver declarations
│   ├── java/com/fluentrevise/mathquiz/
│   │   ├── MainActivity.kt          # Xin quyền + khởi động service
│   │   ├── QuizOverlayService.kt    # Core: timer + overlay window
│   │   └── BootReceiver.kt          # Tự start lại sau reboot
│   └── res/
│       ├── layout/activity_main.xml # UI màn hình chính
│       ├── layout/overlay_quiz.xml  # UI popup toán
│       ├── drawable/                # Backgrounds, gradients
│       └── anim/shake.xml           # Hiệu ứng rung khi sai
├── .github/workflows/build-apk.yml  # CI/CD GitHub Actions
└── ...
```

---

## 🚀 Build APK với GitHub Actions

### Tự động (recommended)

1. Push code lên GitHub repo
2. GitHub Actions tự build → vào tab **Actions** → chọn workflow → tải APK từ **Artifacts**

### Ký APK Release (tuỳ chọn)

Thêm các **Secrets** vào GitHub repo (`Settings → Secrets → Actions`):

| Secret | Giá trị |
|---|---|
| `KEYSTORE_BASE64` | `base64 -i release.keystore` |
| `KEYSTORE_PASSWORD` | Mật khẩu keystore |
| `KEY_ALIAS` | Alias của key |
| `KEY_PASSWORD` | Mật khẩu key |

---

## 📲 Cài & chạy trên điện thoại

1. Tải APK từ GitHub Actions Artifacts
2. Mở app → **Cấp quyền "Hiển thị đè lên ứng dụng khác"**
3. App tự chạy ngầm — sau 10 giây sẽ hiện popup đầu tiên

> **Lưu ý Android 13+**: Cần cấp quyền `POST_NOTIFICATIONS` để hiển thị notification của foreground service.

---

## ⚙️ Tuỳ chỉnh

Để thay đổi thời gian (mặc định 5 phút), sửa trong `QuizOverlayService.kt`:

```kotlin
private val QUIZ_INTERVAL_MS = 5 * 60 * 1000L  // đổi 5 thành số phút bạn muốn
```

---

## 🔐 Quyền cần thiết

| Quyền | Mục đích |
|---|---|
| `SYSTEM_ALERT_WINDOW` | Vẽ overlay đè lên app khác |
| `FOREGROUND_SERVICE` | Chạy service không bị kill |
| `WAKE_LOCK` | Giữ CPU / thức màn hình |
| `RECEIVE_BOOT_COMPLETED` | Auto-start sau reboot |
| `POST_NOTIFICATIONS` | Hiện notification foreground |
