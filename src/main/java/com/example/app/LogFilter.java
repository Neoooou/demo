package com.example.app;

import com.google.common.collect.Lists;

import javax.servlet.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author neo.zr
 * @since 2023/11/13
 */

public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    public static void main(String[] args) {
        final String a = "{\n" +
                "  \"headers\": {},\n" +
                "  \"mtopControl\": true,\n" +
                "  \"success\": true,\n" +
                "  \"model\": {\n" +
                "    \"configId\": \"bmo_sg_prod_1702915200\",\n" +
                "    \"backgroundImage\": \"https://gcp-img.slatic.net/basecamp/images/OSS_4ZFMyXas_08130e4881f44b48a788fc26b6d28492.jpg\",\n" +
                "    \"sellEndTime\": 1703001599000,\n" +
                "    \"sellingPoint\": \"Today Only\",\n" +
                "    \"class\": \"com.lazada.lazmall.bmo.facade.response.BmoStoreModuleResponse\",\n" +
                "    \"sellStartTime\": 1702915200000,\n" +
                "    \"itemSkuDTOS\": [\n" +
                "      {\n" +
                "        \"skuPromotionGroup\": null,\n" +
                "        \"itemDiscountPriceFormatter\": null,\n" +
                "        \"trackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A208.05%3Bitem_id%3A984796259%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A25%3Babid%3A-1%3Bfs_item_price%3A219.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A5.00%25\",\n" +
                "        \"isCurrencyLeft\": \"1\",\n" +
                "        \"itemTitle\": \"Kipling OLINA Rose Black Shoulder Bag\",\n" +
                "        \"shopLogo\": null,\n" +
                "        \"itemSoldCnt\": 25,\n" +
                "        \"clickTrackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A208.05%3Bitem_id%3A984796259%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A25%3Babid%3A-1%3Bfs_item_price%3A219.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A5.00%25\",\n" +
                "        \"itemDiscount\": \"5%\",\n" +
                "        \"shopName\": null,\n" +
                "        \"shopUrl\": null,\n" +
                "        \"itemId\": 984796259,\n" +
                "        \"itemImg\": \"https://sg-live-01.slatic.net/p/8382354c927e1b5caaaa193be7cf47a8.png\",\n" +
                "        \"sellerId\": 1000063876,\n" +
                "        \"voucherInfo\": null,\n" +
                "        \"itemDiscountPrice\": \"208.05\",\n" +
                "        \"itemPriceFormatter\": null,\n" +
                "        \"itemPrice\": \"219.00\",\n" +
                "        \"currency\": \"$\",\n" +
                "        \"shopId\": 154588,\n" +
                "        \"class\": \"com.lazada.lazmall.bmo.facade.model.BmoItemDTO\",\n" +
                "        \"brandLogo\": null,\n" +
                "        \"itemUrl\": \"//www.lazada.sg/products/kipling-olina-rose-black-shoulder-bag-i984796259-s3556058966.html?search=activity&priceCompare=skuId%3A3556058966%3Bsource%3Atpp-recommend-plugin-32941%3Bsn%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3BoriginPrice%3A20805%3BdisplayPrice%3A20805%3BsinglePromotionId%3A91471136763577%3BsingleToolCode%3ApromPrice%3BvoucherPricePlugin%3A0%3Btimestamp%3A1702956791568\",\n" +
                "        \"skuId\": 3556058966\n" +
                "      },\n" +
                "      {\n" +
                "        \"skuPromotionGroup\": null,\n" +
                "        \"itemDiscountPriceFormatter\": null,\n" +
                "        \"trackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A179.55%3Bitem_id%3A1959407931%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A15%3Babid%3A-1%3Bfs_item_price%3A189.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A5.00%25\",\n" +
                "        \"isCurrencyLeft\": \"1\",\n" +
                "        \"itemTitle\": \"Kipling ART M Signature Emb Tote Bag\",\n" +
                "        \"shopLogo\": null,\n" +
                "        \"itemSoldCnt\": 15,\n" +
                "        \"clickTrackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A179.55%3Bitem_id%3A1959407931%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A15%3Babid%3A-1%3Bfs_item_price%3A189.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A5.00%25\",\n" +
                "        \"itemDiscount\": \"5%\",\n" +
                "        \"shopName\": null,\n" +
                "        \"shopUrl\": null,\n" +
                "        \"itemId\": 1959407931,\n" +
                "        \"itemImg\": \"https://sg-test-11.slatic.net/p/97ccb7febd40a6cea98ecf595a028ea2.jpg\",\n" +
                "        \"sellerId\": 1000063876,\n" +
                "        \"voucherInfo\": null,\n" +
                "        \"itemDiscountPrice\": \"179.55\",\n" +
                "        \"itemPriceFormatter\": null,\n" +
                "        \"itemPrice\": \"189.00\",\n" +
                "        \"currency\": \"$\",\n" +
                "        \"shopId\": 154588,\n" +
                "        \"class\": \"com.lazada.lazmall.bmo.facade.model.BmoItemDTO\",\n" +
                "        \"brandLogo\": null,\n" +
                "        \"itemUrl\": \"//www.lazada.sg/products/kipling-art-m-signature-emb-tote-bag-i1959407931-s10522546915.html?search=activity&priceCompare=skuId%3A10522546915%3Bsource%3Atpp-recommend-plugin-32941%3Bsn%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3BoriginPrice%3A17955%3BdisplayPrice%3A17955%3BsinglePromotionId%3A91471136763577%3BsingleToolCode%3ApromPrice%3BvoucherPricePlugin%3A0%3Btimestamp%3A1702956791568\",\n" +
                "        \"skuId\": 10522546915\n" +
                "      },\n" +
                "      {\n" +
                "        \"skuPromotionGroup\": null,\n" +
                "        \"itemDiscountPriceFormatter\": null,\n" +
                "        \"trackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A95.2%3Bitem_id%3A1959450632%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A32%3Babid%3A-1%3Bfs_item_price%3A119.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A20.00%25\",\n" +
                "        \"isCurrencyLeft\": \"1\",\n" +
                "        \"itemTitle\": \"Kipling ABANU MULTI Crossbody Bag\",\n" +
                "        \"shopLogo\": null,\n" +
                "        \"itemSoldCnt\": 32,\n" +
                "        \"clickTrackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A95.2%3Bitem_id%3A1959450632%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A32%3Babid%3A-1%3Bfs_item_price%3A119.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A20.00%25\",\n" +
                "        \"itemDiscount\": \"20%\",\n" +
                "        \"shopName\": null,\n" +
                "        \"shopUrl\": null,\n" +
                "        \"itemId\": 1959450632,\n" +
                "        \"itemImg\": \"https://sg-test-11.slatic.net/p/cd4c2322379ace7de86da151ed2e9fce.jpg\",\n" +
                "        \"sellerId\": 1000063876,\n" +
                "        \"voucherInfo\": null,\n" +
                "        \"itemDiscountPrice\": \"95.20\",\n" +
                "        \"itemPriceFormatter\": null,\n" +
                "        \"itemPrice\": \"119.00\",\n" +
                "        \"currency\": \"$\",\n" +
                "        \"shopId\": 154588,\n" +
                "        \"class\": \"com.lazada.lazmall.bmo.facade.model.BmoItemDTO\",\n" +
                "        \"brandLogo\": null,\n" +
                "        \"itemUrl\": \"//www.lazada.sg/products/kipling-abanu-multi-crossbody-bag-i1959450632-s19818303665.html?search=activity&priceCompare=skuId%3A19818303665%3Bsource%3Atpp-recommend-plugin-32941%3Bsn%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3BoriginPrice%3A9520%3BdisplayPrice%3A9520%3BsinglePromotionId%3A91471136763577%3BsingleToolCode%3ApromPrice%3BvoucherPricePlugin%3A0%3Btimestamp%3A1702956791568\",\n" +
                "        \"skuId\": 19818303665\n" +
                "      },\n" +
                "      {\n" +
                "        \"skuPromotionGroup\": null,\n" +
                "        \"itemDiscountPriceFormatter\": null,\n" +
                "        \"trackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A103.4%3Bitem_id%3A1959469529%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A33%3Babid%3A-1%3Bfs_item_price%3A159.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A34.97%25\",\n" +
                "        \"isCurrencyLeft\": \"1\",\n" +
                "        \"itemTitle\": \"Kipling City Pack S Backpack\",\n" +
                "        \"shopLogo\": null,\n" +
                "        \"itemSoldCnt\": 33,\n" +
                "        \"clickTrackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A103.4%3Bitem_id%3A1959469529%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A33%3Babid%3A-1%3Bfs_item_price%3A159.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A34.97%25\",\n" +
                "        \"itemDiscount\": \"35%\",\n" +
                "        \"shopName\": null,\n" +
                "        \"shopUrl\": null,\n" +
                "        \"itemId\": 1959469529,\n" +
                "        \"itemImg\": \"https://sg-test-11.slatic.net/p/442cb81f42b3f6060ae380f7d096fcbe.jpg\",\n" +
                "        \"sellerId\": 1000063876,\n" +
                "        \"voucherInfo\": null,\n" +
                "        \"itemDiscountPrice\": \"103.40\",\n" +
                "        \"itemPriceFormatter\": null,\n" +
                "        \"itemPrice\": \"159.00\",\n" +
                "        \"currency\": \"$\",\n" +
                "        \"shopId\": 154588,\n" +
                "        \"class\": \"com.lazada.lazmall.bmo.facade.model.BmoItemDTO\",\n" +
                "        \"brandLogo\": null,\n" +
                "        \"itemUrl\": \"//www.lazada.sg/products/kipling-city-pack-s-backpack-i1959469529-s19597430621.html?search=activity&priceCompare=skuId%3A19597430621%3Bsource%3Atpp-recommend-plugin-32941%3Bsn%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3BoriginPrice%3A10340%3BdisplayPrice%3A10340%3BsinglePromotionId%3A91471136763577%3BsingleToolCode%3ApromPrice%3BvoucherPricePlugin%3A0%3Btimestamp%3A1702956791568\",\n" +
                "        \"skuId\": 19597430621\n" +
                "      },\n" +
                "      {\n" +
                "        \"skuPromotionGroup\": null,\n" +
                "        \"itemDiscountPriceFormatter\": null,\n" +
                "        \"trackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A160.55%3Bitem_id%3A1959504228%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A95%3Babid%3A-1%3Bfs_item_price%3A169.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A5.00%25\",\n" +
                "        \"isCurrencyLeft\": \"1\",\n" +
                "        \"itemTitle\": \"Kipling Gabbie Crossbody Bag\",\n" +
                "        \"shopLogo\": null,\n" +
                "        \"itemSoldCnt\": 95,\n" +
                "        \"clickTrackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A160.55%3Bitem_id%3A1959504228%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A95%3Babid%3A-1%3Bfs_item_price%3A169.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A5.00%25\",\n" +
                "        \"itemDiscount\": \"5%\",\n" +
                "        \"shopName\": null,\n" +
                "        \"shopUrl\": null,\n" +
                "        \"itemId\": 1959504228,\n" +
                "        \"itemImg\": \"https://sg-test-11.slatic.net/p/0fe98a7f73843038a9a98c928c2ff359.jpg\",\n" +
                "        \"sellerId\": 1000063876,\n" +
                "        \"voucherInfo\": null,\n" +
                "        \"itemDiscountPrice\": \"160.55\",\n" +
                "        \"itemPriceFormatter\": null,\n" +
                "        \"itemPrice\": \"169.00\",\n" +
                "        \"currency\": \"$\",\n" +
                "        \"shopId\": 154588,\n" +
                "        \"class\": \"com.lazada.lazmall.bmo.facade.model.BmoItemDTO\",\n" +
                "        \"brandLogo\": null,\n" +
                "        \"itemUrl\": \"//www.lazada.sg/products/kipling-gabbie-crossbody-bag-i1959504228-s10522678069.html?search=activity&priceCompare=skuId%3A10522678069%3Bsource%3Atpp-recommend-plugin-32941%3Bsn%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3BoriginPrice%3A16055%3BdisplayPrice%3A16055%3BsinglePromotionId%3A91471136763577%3BsingleToolCode%3ApromPrice%3BvoucherPricePlugin%3A0%3Btimestamp%3A1702956791568\",\n" +
                "        \"skuId\": 10522678069\n" +
                "      },\n" +
                "      {\n" +
                "        \"skuPromotionGroup\": null,\n" +
                "        \"itemDiscountPriceFormatter\": null,\n" +
                "        \"trackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A141.55%3Bitem_id%3A1959524157%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A15%3Babid%3A-1%3Bfs_item_price%3A149.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A5.00%25\",\n" +
                "        \"isCurrencyLeft\": \"1\",\n" +
                "        \"itemTitle\": \"Kipling ASSENI S Signature Emb Tote Bag\",\n" +
                "        \"shopLogo\": null,\n" +
                "        \"itemSoldCnt\": 15,\n" +
                "        \"clickTrackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A141.55%3Bitem_id%3A1959524157%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A15%3Babid%3A-1%3Bfs_item_price%3A149.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A5.00%25\",\n" +
                "        \"itemDiscount\": \"5%\",\n" +
                "        \"shopName\": null,\n" +
                "        \"shopUrl\": null,\n" +
                "        \"itemId\": 1959524157,\n" +
                "        \"itemImg\": \"https://sg-live-01.slatic.net/p/fa9b40c6539d16c8a317dda65406f98e.jpg\",\n" +
                "        \"sellerId\": 1000063876,\n" +
                "        \"voucherInfo\": null,\n" +
                "        \"itemDiscountPrice\": \"141.55\",\n" +
                "        \"itemPriceFormatter\": null,\n" +
                "        \"itemPrice\": \"149.00\",\n" +
                "        \"currency\": \"$\",\n" +
                "        \"shopId\": 154588,\n" +
                "        \"class\": \"com.lazada.lazmall.bmo.facade.model.BmoItemDTO\",\n" +
                "        \"brandLogo\": null,\n" +
                "        \"itemUrl\": \"//www.lazada.sg/products/kipling-asseni-s-signature-emb-tote-bag-i1959524157-s10522622634.html?search=activity&priceCompare=skuId%3A10522622634%3Bsource%3Atpp-recommend-plugin-32941%3Bsn%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3BoriginPrice%3A14155%3BdisplayPrice%3A14155%3BsinglePromotionId%3A91471136763577%3BsingleToolCode%3ApromPrice%3BvoucherPricePlugin%3A0%3Btimestamp%3A1702956791568\",\n" +
                "        \"skuId\": 10522622634\n" +
                "      },\n" +
                "      {\n" +
                "        \"skuPromotionGroup\": null,\n" +
                "        \"itemDiscountPriceFormatter\": null,\n" +
                "        \"trackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A141.55%3Bitem_id%3A1959533104%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A14%3Babid%3A-1%3Bfs_item_price%3A149.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A5.00%25\",\n" +
                "        \"isCurrencyLeft\": \"1\",\n" +
                "        \"itemTitle\": \"Kipling ART MINI Signature Emb Shoulder Bag\",\n" +
                "        \"shopLogo\": null,\n" +
                "        \"itemSoldCnt\": 14,\n" +
                "        \"clickTrackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A141.55%3Bitem_id%3A1959533104%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A14%3Babid%3A-1%3Bfs_item_price%3A149.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A5.00%25\",\n" +
                "        \"itemDiscount\": \"5%\",\n" +
                "        \"shopName\": null,\n" +
                "        \"shopUrl\": null,\n" +
                "        \"itemId\": 1959533104,\n" +
                "        \"itemImg\": \"https://sg-test-11.slatic.net/p/d9e0448d5eb2e0d31b0936204d9b105f.jpg\",\n" +
                "        \"sellerId\": 1000063876,\n" +
                "        \"voucherInfo\": null,\n" +
                "        \"itemDiscountPrice\": \"141.55\",\n" +
                "        \"itemPriceFormatter\": null,\n" +
                "        \"itemPrice\": \"149.00\",\n" +
                "        \"currency\": \"$\",\n" +
                "        \"shopId\": 154588,\n" +
                "        \"class\": \"com.lazada.lazmall.bmo.facade.model.BmoItemDTO\",\n" +
                "        \"brandLogo\": null,\n" +
                "        \"itemUrl\": \"//www.lazada.sg/products/kipling-art-mini-signature-emb-shoulder-bag-i1959533104-s10522554786.html?search=activity&priceCompare=skuId%3A10522554786%3Bsource%3Atpp-recommend-plugin-32941%3Bsn%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3BoriginPrice%3A14155%3BdisplayPrice%3A14155%3BsinglePromotionId%3A91471136763577%3BsingleToolCode%3ApromPrice%3BvoucherPricePlugin%3A0%3Btimestamp%3A1702956791568\",\n" +
                "        \"skuId\": 10522554786\n" +
                "      },\n" +
                "      {\n" +
                "        \"skuPromotionGroup\": null,\n" +
                "        \"itemDiscountPriceFormatter\": null,\n" +
                "        \"trackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A152.1%3Bitem_id%3A1959554009%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A20%3Babid%3A-1%3Bfs_item_price%3A169.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A10.00%25\",\n" +
                "        \"isCurrencyLeft\": \"1\",\n" +
                "        \"itemTitle\": \"Kipling ART Signature Emb Tote Bag\",\n" +
                "        \"shopLogo\": null,\n" +
                "        \"itemSoldCnt\": 20,\n" +
                "        \"clickTrackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A152.1%3Bitem_id%3A1959554009%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A20%3Babid%3A-1%3Bfs_item_price%3A169.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A10.00%25\",\n" +
                "        \"itemDiscount\": \"10%\",\n" +
                "        \"shopName\": null,\n" +
                "        \"shopUrl\": null,\n" +
                "        \"itemId\": 1959554009,\n" +
                "        \"itemImg\": \"https://sg-test-11.slatic.net/p/798a197dbb5e634b3da52fef6690cb41.png\",\n" +
                "        \"sellerId\": 1000063876,\n" +
                "        \"voucherInfo\": null,\n" +
                "        \"itemDiscountPrice\": \"152.10\",\n" +
                "        \"itemPriceFormatter\": null,\n" +
                "        \"itemPrice\": \"169.00\",\n" +
                "        \"currency\": \"$\",\n" +
                "        \"shopId\": 154588,\n" +
                "        \"class\": \"com.lazada.lazmall.bmo.facade.model.BmoItemDTO\",\n" +
                "        \"brandLogo\": null,\n" +
                "        \"itemUrl\": \"//www.lazada.sg/products/kipling-art-signature-emb-tote-bag-i1959554009-s10522566686.html?search=activity&priceCompare=skuId%3A10522566686%3Bsource%3Atpp-recommend-plugin-32941%3Bsn%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3BoriginPrice%3A15210%3BdisplayPrice%3A15210%3BsinglePromotionId%3A91471136763577%3BsingleToolCode%3ApromPrice%3BvoucherPricePlugin%3A0%3Btimestamp%3A1702956791568\",\n" +
                "        \"skuId\": 10522566686\n" +
                "      },\n" +
                "      {\n" +
                "        \"skuPromotionGroup\": null,\n" +
                "        \"itemDiscountPriceFormatter\": null,\n" +
                "        \"trackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A142.00%3Bitem_id%3A2068542378%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A6%3Babid%3A-1%3Bfs_item_price%3A189.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A5.00%25\",\n" +
                "        \"isCurrencyLeft\": \"1\",\n" +
                "        \"itemTitle\": \"Kipling MASHA Black FL Shoulder Bag\",\n" +
                "        \"shopLogo\": null,\n" +
                "        \"itemSoldCnt\": 6,\n" +
                "        \"clickTrackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A142.00%3Bitem_id%3A2068542378%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A6%3Babid%3A-1%3Bfs_item_price%3A189.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A5.00%25\",\n" +
                "        \"itemDiscount\": \"25%\",\n" +
                "        \"shopName\": null,\n" +
                "        \"shopUrl\": null,\n" +
                "        \"itemId\": 2068542378,\n" +
                "        \"itemImg\": \"https://sg-test-11.slatic.net/p/c827ab28350cc80e821bbf89afe2ae4a.jpg\",\n" +
                "        \"sellerId\": 1000063876,\n" +
                "        \"voucherInfo\": null,\n" +
                "        \"itemDiscountPrice\": \"142.00\",\n" +
                "        \"itemPriceFormatter\": null,\n" +
                "        \"itemPrice\": \"189.00\",\n" +
                "        \"currency\": \"$\",\n" +
                "        \"shopId\": 154588,\n" +
                "        \"class\": \"com.lazada.lazmall.bmo.facade.model.BmoItemDTO\",\n" +
                "        \"brandLogo\": null,\n" +
                "        \"itemUrl\": \"//www.lazada.sg/products/kipling-masha-black-fl-shoulder-bag-i2068542378-s11385646949.html?search=activity&priceCompare=skuId%3A11385646949%3Bsource%3Atpp-recommend-plugin-32941%3Bsn%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3BoriginPrice%3A14200%3BdisplayPrice%3A14200%3BsinglePromotionId%3A-1%3BsingleToolCode%3AmockedSalePrice%3BvoucherPricePlugin%3A0%3Btimestamp%3A1702956791568\",\n" +
                "        \"skuId\": 11385646949\n" +
                "      },\n" +
                "      {\n" +
                "        \"skuPromotionGroup\": null,\n" +
                "        \"itemDiscountPriceFormatter\": null,\n" +
                "        \"trackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A90.4%3Bitem_id%3A2173333824%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A5%3Babid%3A-1%3Bfs_item_price%3A139.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A34.96%25\",\n" +
                "        \"isCurrencyLeft\": \"1\",\n" +
                "        \"itemTitle\": \"Kipling IZELLAH Soft Dot Yellow Crossbody Bag\",\n" +
                "        \"shopLogo\": null,\n" +
                "        \"itemSoldCnt\": 5,\n" +
                "        \"clickTrackInfo\": \"rs%3A0.0%3Bfs_item_discount_price%3A90.4%3Bitem_id%3A2173333824%3Bmt%3Ahot%3Bfs_utdid%3A-1%3Bfs_item_sold_cnt%3A5%3Babid%3A-1%3Bfs_item_price%3A139.00%3Bpvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Bfs_min_price_l30d%3A0%3Bdata_type%3Aactivity%3Bfs_pvid%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3Btime%3A1702956791%3Bfs_biz_type%3Abmo%3Bscm%3A1007.42941.-1.%3Bchannel_id%3A0000%3Bfs_item_discount%3A34.96%25\",\n" +
                "        \"itemDiscount\": \"35%\",\n" +
                "        \"shopName\": null,\n" +
                "        \"shopUrl\": null,\n" +
                "        \"itemId\": 2173333824,\n" +
                "        \"itemImg\": \"https://sg-test-11.slatic.net/p/3cb8e0809a828e5c0c02a1bfb5c0553c.jpg\",\n" +
                "        \"sellerId\": 1000063876,\n" +
                "        \"voucherInfo\": null,\n" +
                "        \"itemDiscountPrice\": \"90.40\",\n" +
                "        \"itemPriceFormatter\": null,\n" +
                "        \"itemPrice\": \"139.00\",\n" +
                "        \"currency\": \"$\",\n" +
                "        \"shopId\": 154588,\n" +
                "        \"class\": \"com.lazada.lazmall.bmo.facade.model.BmoItemDTO\",\n" +
                "        \"brandLogo\": null,\n" +
                "        \"itemUrl\": \"//www.lazada.sg/products/kipling-izellah-soft-dot-yellow-crossbody-bag-i2173333824-s12408127292.html?search=activity&priceCompare=skuId%3A12408127292%3Bsource%3Atpp-recommend-plugin-32941%3Bsn%3Afa8848cc-3497-4e7f-8174-8d575f0e62ed%3BoriginPrice%3A9040%3BdisplayPrice%3A9040%3BsinglePromotionId%3A91471136763577%3BsingleToolCode%3ApromPrice%3BvoucherPricePlugin%3A0%3Btimestamp%3A1702956791568\",\n" +
                "        \"skuId\": 12408127292\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"bizExtMap\": null,\n" +
                "  \"mappingCode\": null,\n" +
                "  \"class\": \"com.taobao.mtop.common.MtopResult\",\n" +
                "  \"msgInfo\": null,\n" +
                "  \"msgCode\": null,\n" +
                "  \"httpStatusCode\": 200\n" +
                "}";
        System.out.println(a.getBytes(StandardCharsets.UTF_8).length);
    }
}
