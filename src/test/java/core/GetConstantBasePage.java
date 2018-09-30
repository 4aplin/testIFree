//package core;
//
//import com.gargoylesoftware.htmlunit.util.NameValuePair;
//import core.api.Api;
//import core.api.jsonResponse.*;
//import org.junit.Assert;
//import java.util.ArrayList;
//
//public class GetConstantBasePage extends SetUrlTestBase
//{
//    public void RemoveAllSocBookmarks(String id)
//    {
//        apiRequest = Authorization(id);
////        Get list
//        ResponseListSocBookmarks responseList =
//                (ResponseListSocBookmarks)apiRequest.GET("user/listSocBookmarks", ResponseListSocBookmarks.class);
//        if (responseList.list.size() == 0) return;
////        Delete items
//        for(MessageListSocBookmarks item : responseList.list)
//        {
//            RemoveSocBookmarksLink(item.link, id);
//        }
//    }
//
//    public void RemoveSocBookmarksLink(String link, String id)
//    {
//        apiRequest = Authorization(id);
//        String link1 = link.replace("/", "%2F");
//        TResponse responseRemoveSocBookmarksLink =
//                (TResponse)apiRequest.DELETE("user/removeSocBookmark?link=" + link1,
//                        SetApiValue( new String[][] {
//                                {"link", link}
//                        }),
//                        TResponse.class);
//        Assert.assertTrue(responseRemoveSocBookmarksLink.success);
//
//        responseRemoveSocBookmarksLink =
//            (TResponse)apiRequest.DELETE("user/removeSocBookmark?link=" + link,
//                    SetApiValue( new String[][] {
//                            {"link", link}
//                    }),
//                    TResponse.class);
//        Assert.assertTrue(responseRemoveSocBookmarksLink.success);
//    }
//
//    public void AddSocBookmarks(String id, String link, String  socNetwork)
//    {
//        apiRequest = Authorization(id);
////        Add link
//        String responseAddLink =
//                (String)apiRequest.POST("user/addSocBookmark",
//                        SetApiValue( new String[][] {
//                                {"link", link},
//                                {"soc_network", socNetwork},
//                        }),
//                        TResponse.class);
//        Assert.assertTrue(responseAddLink.equals("true"));
//    }
//
//    public void DeleteAllItemById(String id)
//    {
//        apiRequest = Authorization(id);
////        Get items list
//        ResponseItems responseItems =
//                (ResponseItems)apiRequest.GET("wardrobe/nextitems?",
//                        SetApiValue( new String[][] {
//                                {"records_per_page", "20"},
//                                {"last_record_id", ""},
//                                {"item_group_id", ""},
//                                {"onlyConfirmed", "0"}
//                        }),
//                        ResponseItems.class);
//        if (responseItems.data.size() == 0) return;
////        Delete items
//        for(MessageItems item : responseItems.data)
//        {
//                TResponse responseItemDelete =
//                        (TResponse)apiRequest.POST("wardrobe/activateItem",
//                                SetApiValue( new String[][] {
//                                        {"item_id", id}
//                                }),
//                                TResponse.class);
//
//            DeleteItemById(item.item_id, item.last_modified);
//        }
//    }
//
//    private void DeleteItemById(String id, String lastModified)
//    {
//        if (!lastModified.contains("-")) {
//            TResponse responseItemDelete =
//                    (TResponse) apiRequest.PUT("wardrobe/declinePurchase",
//                            SetApiValue(new String[][]{
//                                    {"itemId", id},
//                                    {"dtime", lastModified}
//                            }),
//                            TResponse.class);
//            try {
//                Assert.assertTrue(responseItemDelete.success);
//            }
//            catch (NullPointerException e){}
//            catch (AssertionError e){}
//        }
//        else {
//            TResponse responseItemDelete =
//                    (TResponse) apiRequest.DELETE("wardrobe/deleteitem?",
//                            SetApiValue(new String[][]{
//                                    {"id", id},
//                                    {"last_modified", lastModified}
//                            }),
//                            TResponse.class);
//            try {
//                Assert.assertTrue(responseItemDelete.success);
//            }
//            catch (NullPointerException e){}
//            catch (AssertionError e){}
//        }
//
//
//
//    }
//
//    public void DeleteItemByIdByModelName(String id, String modelName)
//    {
//        apiRequest = Authorization(id);
////        Get items list
//        ResponseItems responseItems =
//                (ResponseItems)apiRequest.GET("wardrobe/nextitems?",
//                        SetApiValue( new String[][] {
//                                {"records_per_page", "20"},
//                                {"last_record_id", ""},
//                                {"item_group_id", ""},
//                                {"onlyConfirmed", "0"}
//                        }),
//                        ResponseItems.class);
//        if (responseItems.data.size() == 0) return;
//
////        Delete item
//        for(MessageItems item : responseItems.data)
//        {
//            if (item.model.equalsIgnoreCase(modelName)) {
//            TResponse responseItemDelete =
//                    (TResponse) apiRequest.POST("wardrobe/activateItem",
//                            SetApiValue(new String[][]{
//                                    {"item_id", id}
//                            }),
//                            TResponse.class);
//
//            DeleteItemById(item.item_id, item.last_modified);
//        }
//        }
//    }
//
//    public String GetVerificationCodeByEmail(String userId, String email)
//    {
//        apiRequest = Authorization(userId);
//        ResponseUsers responseUsers =
//                (ResponseUsers)apiRequest.GET("developer/getUsers?",
//                        SetApiValue( new String[][] {
//                                {"limit", "20000"}
//                        }),
//                        ResponseUsers.class);
//        for (MessageUsers user : responseUsers.data) {
//            try {
//                if (user.email.equals(email))
//                    return user.verification_code;
//            }catch (NullPointerException e){}
//        }
//        return  null;
//    }
//
//    public Api Authorization(String id)
//    {
//        apiRequest = new Api(apiUrl);
//        TResponse response =
//                (TResponse)apiRequest.POST("developer/switchUser?",
//                        SetApiValue( new String[][] {
//                                {"profile_id", id}
//                        }),
//                        TResponse.class);
//        Assert.assertTrue(response.success);
//        return apiRequest;
//    }
//
//    public void DeleteWardrobeProfilesById(String id, String userId)
//    {
//        apiRequest = Authorization(userId);
////        Get profile list
//        ResponseUserData responseProfileDate = GetProfileDate();
//        for(MessageUsers user : responseProfileDate.profiles)
//        {
//            if (!user.id.equals(id))
//                DeleteProfileUserById(user.id, userId);
//        }
//    }
//
//    public ResponseUserData GetProfileDate()
//    {
//    ResponseUserData responseProfileDate =
//            (ResponseUserData)apiRequest.GET("userdata", ResponseUserData.class);
//        return responseProfileDate;
//    }
//
//    public void DeleteWardrobeProfilesForIdByName(String id, String name)
//    {
//        apiRequest = Authorization(id);
////        Get profile list
//        ResponseUserData responseProfileDate =
//                (ResponseUserData)apiRequest.GET("userdata",
//                        ResponseUserData.class);
//
//        for(MessageUsers user : responseProfileDate.profiles)
//        {
//            if (user.name.equalsIgnoreCase(name))
//                DeleteProfileUserById(user.id, id);
//        }
//    }
//
//    public void DeleteCollectionForIdByName(String id, String name)
//    {
//        apiRequest = Authorization(id);
////        Get collection list
//        ResponseLookCollections responseLookCollections =
//                (ResponseLookCollections)apiRequest.POST("looks/userCollections?userID=" + id,
//                        ResponseLookCollections.class);
//
//        for(MessageLookCollections collection : responseLookCollections.data)
//        {
//            if (collection.title.equalsIgnoreCase(name))
//                DeleteLookCollectionById(collection.albumId, id);
//        }
//    }
//
//    public void DeleteCollectionAllForIdByName(String id)
//    {
//        apiRequest = Authorization(id);
////        Get collection list
//        ResponseLookCollections responseLookCollections =
//                (ResponseLookCollections)apiRequest.POST("looks/userCollections?userID=" + id,
//                        ResponseLookCollections.class);
//
//        for(MessageLookCollections collection : responseLookCollections.data)
//        {
//            DeleteLookCollectionById(collection.albumId, id);
//        }
//    }
//
//    public void DeleteCollectionByName(String id, String name)
//    {
//        apiRequest = Authorization(id);
////        Get collection list
//        ResponseLookCollections responseLookCollections =
//                (ResponseLookCollections)apiRequest.POST("looks/userCollections?userID=" + id, ResponseLookCollections.class);
//
//        for(MessageLookCollections collection : responseLookCollections.data)
//        {
//            if (collection.title.equalsIgnoreCase(name))
//                DeleteLookCollectionById(collection.albumId, id);
//        }
//    }
//
//    public void AddCollectionForIdByName(String id, String name)
//    {
//        apiRequest = Authorization(id);
////        Get collection list
//        TResponse AddLookCollections =
//                (TResponse)apiRequest.POST("looks/addCollection",
//                        SetApiValue( new String[][] {
//                                {"title", name},
//                        }), TResponse.class);
//        Assert.assertTrue(AddLookCollections.success);
//    }
//
//    public void DeleteLookCollectionById(String albumId, String id)
//    {
//        apiRequest = Authorization(id);
////        Delete look collection
//        TResponse responseLookCollectionDelete =
//                (TResponse)apiRequest.DELETE("deleteCollection?",
//                        SetApiValue( new String[][] {
//                                {"collectionId", albumId},
//                                {"moveLooks", "1"}
//                        }), TResponse.class);
//        Assert.assertTrue(responseLookCollectionDelete.success);
//    }
//
//    public void DeleteProfileUserById(String id, String userId)
//    {
//        apiRequest = Authorization(userId);
////        Delete user profile
//        TResponse responseProfileDelete =
//                (TResponse)apiRequest.DELETE("removeUserProfile?",
//                        SetApiValue( new String[][] {
//                                {"id", id}
//                        }), TResponse.class);
//        Assert.assertTrue(responseProfileDelete.success);
//    }
//
//    public String AddProfileWardrobeForUserId(String id, String name, String gender)
//    {
//        apiRequest = Authorization(id);
////        add user profile wardrobe
//        TResponse responseProfileAdd =
//                (TResponse)apiRequest.POST("createNewUser",
//                        SetApiValue( new String[][] {
//                                {"name", name},
//                                {"gender", gender}
//                        }), TResponse.class);
//        Assert.assertTrue(responseProfileAdd.success);
//        return  responseProfileAdd.user_id;
//    }
//
//    public String LookAddForUser(String id, String items, String description, String title, String image)
//    {
//        apiRequest = Authorization(id);
////        add Look
//        ResponseLookAdd responseLookAdd =
//                (ResponseLookAdd)apiRequest.PUT("lookEditSave",
//                        SetApiValue( new String[][] {
//                                {"description", description},
//                                {"title", title},
//                                {"items", items},
//                        }), ResponseLookAdd.class);
//        Assert.assertFalse(responseLookAdd.id.isEmpty());
//
////        publish liik
//        TResponse responseLookPublish =
//                (TResponse)apiRequest.POST("looks/publishLook",
//                        SetApiValue( new String[][] {
//                                {"lookId", responseLookAdd.id},
//                        }), TResponse.class);
//        Assert.assertTrue(responseLookPublish.success);
//
//
////        add image
//        TResponse responseLookAddPic =
//                (TResponse)apiRequest.PUT("looks/addPic",
//                        SetApiValue( new String[][] {
//                                {"look_id", responseLookAdd.id},
//                                {"image", image},
//                        }), TResponse.class);
//
//        return responseLookAdd.id;
//    }
//
//    public void AllLooksWishRemove(String id)
//    {
//        apiRequest = Authorization(id);
////        Wish looks list
//        ResponseLooksWish responseLooks =
//                (ResponseLooksWish)apiRequest.GET("wishlist/getLooks?paging_state&records_per_page=10", ResponseLooksWish.class);
//        try{
//        for (MessageLooks look : responseLooks.looks)
//        {
//            LookWishRemoveById(id, look.id);
//        }}
//        catch (NullPointerException e) {}
//    }
//
//    public void LookWishRemoveById(String userId, String lookId){
//        apiRequest = Authorization(userId);
//        apiRequest.DELETE("wishlist/removeLook?look_id=" + lookId, TResponse.class);
//    }
//
//    public void LookWishAddById(String userId, String lookId){
//        apiRequest = Authorization(userId);
//        apiRequest.POST("wishlist/addLook",
//                SetApiValue( new String[][] {
//                        {"id", lookId},
//                }), TResponse.class);
//    }
//
//    public MessageLooks GetLookInfo(String id, String idLook){
////        Get All Looks
//        apiRequest = Authorization(id);
//        MessageLooks responseLooks =
//                (MessageLooks)apiRequest.GET("look/getLookData?id=" + idLook, MessageLooks.class);
//        return responseLooks;
//    }
//
//    public void DeleteAllLooksByUserId(String id){
//        apiRequest = Authorization(id);
////        Get All Looks
//        ResponseLooks responseLooks =
//                (ResponseLooks)apiRequest.GET("looks/looksInCollection?",
//                        SetApiValue( new String[][] {
//                                {"userId", id},
//                        }), ResponseLooks.class);
//        for (MessageLooks look : responseLooks.looksData)
//        {
//            LookDeleteById(id, look.id);
//        }
//    }
//
//    public void DeleteLooksByTitle(String id, String title){
//        apiRequest = Authorization(id);
////        Get All Looks
//        ResponseLooks responseLooks =
//                (ResponseLooks)apiRequest.GET("looks/looksInCollection?",
//                        SetApiValue( new String[][] {
//                                {"userId", id},
//                        }), ResponseLooks.class);
//        for (MessageLooks look : responseLooks.looksData)
//        {
//            if (look.title.equalsIgnoreCase(title))
//                LookDeleteById(id, look.id);
//        }
//    }
//
//    public void LookDeleteById(String userId, String lookId){
//        apiRequest = Authorization(userId);
//        TResponse responseLookDelete =
//                (TResponse)apiRequest.DELETE("lookDelete?look_id=" + lookId,
//                        TResponse.class);
////        Assert.assertTrue(responseLookDelete.success);
//    }
//
//    public ResponseItems GetLookData(String userId, String lookId)
//    {
//        apiRequest = Authorization(userId);
//        ResponseItems responseItems =
//                (ResponseItems) apiRequest.GET("looks/lookItems?look_id=" + lookId,
//             //   (ResponseItems) apiRequest.GET("/look/getLookData?id=" + lookId,
//                        ResponseItems.class);
//        return responseItems;
//    }
//
//    public String AddItemForUserId(String id, String additionalSizes, String brand, String color, String model, String size, String sizeType, String type)
//    {
//        apiRequest = Authorization(id);
////        add user profile wardrobe
//        ResponseItemAdd responseProfileAdd =
//                (ResponseItemAdd)apiRequest.POST("wardrobe/additem",
//                        SetApiValue( new String[][] {
//                                {"additionalSizes", additionalSizes},
//                                {"brand", brand},
//                                {"color", color},
//                                {"model", model},
//                                {"size", size},
//                                {"sizeType", sizeType},
//                                {"type", type}
//                        }), ResponseItemAdd.class);
//        Assert.assertFalse(responseProfileAdd.id.isEmpty());
//        return responseProfileAdd.id;
//    }
//
//    public String GetUserIdByEmail(String email)
//    {
//        ResponseUsers responseUsers =
//                (ResponseUsers)apiRequest.GET("developer/getUsers?limit=20000",
//                        ResponseUsers.class);
////        response 504 time-out.. try again
//        try {responseUsers.data.size();}
//        catch (NullPointerException e){
//            responseUsers =
//                    (ResponseUsers)apiRequest.GET("developer/getUsers?limit=20000",
//                            ResponseUsers.class);
//        }
//
//        for (MessageUsers user : responseUsers.data) {
//            try {
//                if (user.email.equals(email))
//                    return user.id;
//            }
//            catch (NullPointerException e)
//            {}
//        }
//        return null;
//    }
//
//    public String GetUserNameById(String id)
//    {
//        ResponseUsers responseUsers =
//                (ResponseUsers)apiRequest.GET("developer/getUsers?limit=20000",
//                        ResponseUsers.class);
//        for (MessageUsers user : responseUsers.data) {
//            try {
//                if (user.id.equals(id))
//                    return user.name;
//            } catch (NullPointerException e) {
//            }
//        }
//        return null;
//    }
//
//    public void Logout()
//    {
//        apiRequest.POST("logout", TResponse.class);
//    }
//
//    public void ChangeUserProfile(String id)
//    {
//        apiRequest = Authorization(id);
//        TResponse responseUsers =
//                (TResponse)apiRequest.POST("changeUserProfile",
//                        SetApiValue(new String[][] {
//                                {"new_user_id", id}
//                        }), TResponse.class);
//        Assert.assertTrue(responseUsers.success);
//    }
//
//    public ResponseTopInfluencers getTopInfluencers()
//    {
//        ResponseTopInfluencers responseTopInfluencers =
//                (ResponseTopInfluencers) apiRequest.GET("top/followedUsersTop?lastPosition=1&recordsPerPage=4",
//                        ResponseTopInfluencers.class);
//        return responseTopInfluencers;
//            }
//
//    public ResponseLookTheWeek getLookTheWeek()
//    {
//        ResponseLookTheWeek responseLookTheWeek =
//                (ResponseLookTheWeek) apiRequest.GET("top/getLookOfTheWeek",
//                ResponseLookTheWeek.class);
//        return responseLookTheWeek;
//    }
//
//    public ResponseTopLooks getTopLooks()
//    {
//            ResponseTopLooks responseTopLooks =
//                    (ResponseTopLooks) apiRequest.GET("top/getTopLooks",
//                            ResponseTopLooks.class);
//            return responseTopLooks;
//    }
//
//    public ResponseLooksHistory GetLooksHistory(String id)
//    {
//        apiRequest = Authorization(id);
//        ResponseLooksHistory responseLooksHistory =
//                (ResponseLooksHistory) apiRequest.GET("shop/getLooksFromHistory?itemsCount=10&paging_state=",
//                      ResponseLooksHistory.class);
//        return responseLooksHistory;
//    }
//
//    public ResponseLooksHistory GetLooks(String id)
//    {
//        ResponseLooksHistory responseLooks =
//                (ResponseLooksHistory) apiRequest.GET("user/getLooks?paging_state=&user_id=" + id,
//                        ResponseLooksHistory.class);
//        return responseLooks;
//    }
//
//    public ResponseLooksDiscover GetLooksDiscover(String id)
//    {
//        apiRequest = Authorization(id);
//        ResponseLooksDiscover responseLooksDiscover =
//                (ResponseLooksDiscover) apiRequest.GET("shop/getLooks?filter=all",
//                        ResponseLooksDiscover.class);
//        return responseLooksDiscover;
//    }
//
//    public MessageLooks GetLook(String lookId)
//    {
//        MessageLooks responseLooks =
//                (MessageLooks) apiRequest.GET("user/getLook?look_id=" + lookId,
//                        MessageLooks.class);
//        return responseLooks;
//    }
//
//    public void EditUserProfile(String id, String firstName, String lastName)
//    {
//        apiRequest = Authorization(id);
//        TResponse response =
//                (TResponse) apiRequest.PUT("user/editUserProfile",
//                        SetApiValue(new String[][] {
//                                {"firstname", firstName},
//                                {"lastname", lastName},
//                        }), TResponse.class);
//    }
//
//    public ResponseUser GetProfileData(String id)
//    {
//        apiRequest = Authorization(id);
//        ResponseUser response =
//                (ResponseUser) apiRequest.GET("user/getProfileData?",
//                        SetApiValue(new String[][] {
//                                {"profile_id", id},
//                        }), ResponseUser.class);
//        return response;
//    }
//
//    public String UnfollowUserProfile(String id, String id2)
//    {
//        apiRequest = Authorization(id);
//        String response =
//                (String) apiRequest.POST("unfollowUserProfile",
//                        SetApiValue(new String[][] {
//                                {"followedUserId", id2},
//                        }), TResponse.class);
//        return response;
//    }
//
//    public String FollowUserProfile(String id, String id2)
//    {
//        apiRequest = Authorization(id);
//        String response =
//                (String) apiRequest.POST("followUserProfile",
//                        SetApiValue(new String[][] {
//                                {"followedUserId", id2},
//                        }), TResponse.class);
//        Assert.assertTrue(response.equals("true"));
//        return response;
//    }
//
//    public ArrayList<NameValuePair> SetApiValue (String[][] valuePairs)
//    {
//        int count = valuePairs.length;
//        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>(count);
//        for (int i=0; i<count; i++)
//        {
//            nameValuePairs.add(new NameValuePair(valuePairs[i][0], valuePairs[i][1]));
//        }
//        return nameValuePairs;
//    }
//}
//
