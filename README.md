
# Bootstrap

Webアプリケション開発の基底フレームワーク。PlayFrameworkに個人開発用の基底機能を含めている。

## 機能概要

- PlayFramework
- DDD基底クラス群
- Twitter OAuth認証
- アカウントエンティティクラス
- 自動ログオンエンティティクラス

## インストール

githubダウンロードからアプリケーション設定まで。

### セットアップ

    $ git clone git@bitbucket.org:makotohanafusa/bootstrap.git

    $ mv bootstrap YOUR_APPLICATION_NAME

    $ vim YOUR_APPLICATION_NAME/conf/application.conf

        application.name=fav -> YOUR_APPLICATION_NAME 書き換え

    $ play dependencies YOUR_APPLICATION_NAME

    $ play eclipsify YOUR_APPLICATION_NAME

### Eclipseインポート

    File Menu > Import > General > Existing Project > /path/to/YOUR_APPLICATION_NAME

---

Makoto Hanafusa ( @asufana )

