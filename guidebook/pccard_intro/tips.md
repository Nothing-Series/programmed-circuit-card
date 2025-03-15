---
navigation:
  parent: pccard_intro/index.md
  title: ヒント
  icon: pccard:card_programmed_circuit
  position: 20
---

# ヒント
## マルチブロックでアイテムと液体を含むレシピをクラフトしたい
一旦バッファに搬出してパイプで分別する方法は、よく見られるアンチパターンです。  
サブネットワークを使うことでこれらのクラフトをうまく行えます。このmodはサブネットワーク先の機械の回路番号も変更します。  
次の図を見てください。

<GameScene zoom="6" background="transparent">
<ImportStructure src="../structure/provider_interface_storage.snbt" />

<BoxAnnotation color="#dddddd" min="2.7 0 1" max="3 1 2">
        インターフェース
  </BoxAnnotation>

<BoxAnnotation color="#dddddd" min="1 0 0" max="1.3 1 4">
        ストレージバス
  </BoxAnnotation>

<BoxAnnotation color="#dddddd" min="0 0 0" max="1 1 4">
        バス・ハッチ
  </BoxAnnotation>

<IsometricCamera yaw="185" pitch="30" />
</GameScene>

全てのストレージバスの「アクセスできないアイテムの報告」を「はい」にしておきます。無効のままだとブロッキングモードがうまく働きません。  
![](../pic/storage_bus_setting.png)
