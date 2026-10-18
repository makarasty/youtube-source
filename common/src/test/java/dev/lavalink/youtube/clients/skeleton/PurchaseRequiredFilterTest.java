package dev.lavalink.youtube.clients.skeleton;

import com.sedmelluq.discord.lavaplayer.tools.JsonBrowser;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PurchaseRequiredFilterTest {
    // Trimmed from an actual MWEB search response (ytsearch:어벤져스 엔드게임 영화).
    private static final String PREVIEW_BADGE_ITEM = "{\"videoId\":\"VihkgZYwxPo\"," +
        "\"headline\":{\"runs\":[{\"text\":\"어벤져스 : 엔드게임 (더빙판)\"}]}," +
        "\"badges\":[{\"metadataBadgeRenderer\":{\"style\":\"BADGE_STYLE_TYPE_YPC\",\"label\":\"미리보기 중\"}}," +
        "{\"metadataBadgeRenderer\":{\"style\":\"BADGE_STYLE_TYPE_SIMPLE\",\"label\":\"12세 이상 관람가\"}}]}";

    // Trimmed from an actual MWEB search response (ytsearch:겨울왕국).
    private static final String UNAVAILABLE_BADGE_ITEM = "{\"videoId\":\"UqR9cw6Sg4w\"," +
        "\"headline\":{\"runs\":[{\"text\":\"겨울왕국\"}]}," +
        "\"badges\":[{\"metadataBadgeRenderer\":{\"style\":\"BADGE_STYLE_TYPE_SIMPLE\",\"label\":\"전체관람가\"}}," +
        "{\"metadataBadgeRenderer\":{\"style\":\"BADGE_STYLE_TYPE_DISABLED\",\"label\":\"사용 불가\"}}]}";

    // Trimmed from an actual WEB search response (ytsearch:어벤져스 엔드게임 영화).
    private static final String OFFER_BUTTONS_ITEM = "{\"videoId\":\"xjq5NDum_W0\"," +
        "\"title\":{\"runs\":[{\"text\":\"어벤져스 (자막판)\"}]}," +
        "\"offerButtons\":[{\"buttonRenderer\":{\"style\":\"STYLE_PRIMARY\",\"text\":{\"runs\":[{\"text\":\"구매\"}]}}}]," +
        "\"badges\":[{\"metadataBadgeRenderer\":{\"style\":\"BADGE_STYLE_TYPE_SIMPLE\",\"label\":\"12세 이상 관람가\"}}]}";

    private static final String NORMAL_ITEM = "{\"videoId\":\"hA6hldpSTpk\"," +
        "\"title\":{\"runs\":[{\"text\":\"어벤져스 vs 타노스 최종 전투 장면\"}]}," +
        "\"badges\":[{\"metadataBadgeRenderer\":{\"style\":\"BADGE_STYLE_TYPE_SIMPLE\",\"label\":\"4K\"}}]}";

    private static final String NO_BADGES_ITEM = "{\"videoId\":\"2X_qT3FYW1c\"," +
        "\"title\":{\"runs\":[{\"text\":\"어벤져스 엔드게임 최고의 명장면 TOP 10\"}]}}";

    @Test
    public void testPreviewBadgeIsPurchaseRequired() throws IOException {
        Assertions.assertTrue(NonMusicClient.isPurchaseRequired(JsonBrowser.parse(PREVIEW_BADGE_ITEM)));
    }

    @Test
    public void testUnavailableBadgeIsPurchaseRequired() throws IOException {
        Assertions.assertTrue(NonMusicClient.isPurchaseRequired(JsonBrowser.parse(UNAVAILABLE_BADGE_ITEM)));
    }

    @Test
    public void testOfferButtonsArePurchaseRequired() throws IOException {
        Assertions.assertTrue(NonMusicClient.isPurchaseRequired(JsonBrowser.parse(OFFER_BUTTONS_ITEM)));
    }

    @Test
    public void testNormalItemIsNotPurchaseRequired() throws IOException {
        Assertions.assertFalse(NonMusicClient.isPurchaseRequired(JsonBrowser.parse(NORMAL_ITEM)));
    }

    @Test
    public void testMissingBadgesAreNotPurchaseRequired() throws IOException {
        Assertions.assertFalse(NonMusicClient.isPurchaseRequired(JsonBrowser.parse(NO_BADGES_ITEM)));
    }
}
