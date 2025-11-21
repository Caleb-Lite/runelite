package net.runelite.client.plugins.pluginhub.com.jagex.oldscape.pub;

import java.net.URL;
import java.util.concurrent.Future;

public interface OtlTokenRequester
{
	Future<OtlTokenResponse> request(URL url);
}

