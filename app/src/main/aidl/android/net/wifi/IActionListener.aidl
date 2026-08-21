package android.net.wifi;

oneway interface IActionListener
{
    void onSuccess();
    void onFailure(int reason);
}