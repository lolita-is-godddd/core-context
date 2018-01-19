package jp.dip.drocside.drocsideplugin.enums;

public enum CommandInfo {
    TPT_ON("あなたへのテレポートが有効になりました。"),
    TPT_OFF("あなたへのテレポートが無効になりました。"),;

    private String s;

    private CommandInfo(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return this.s;
    }
}
