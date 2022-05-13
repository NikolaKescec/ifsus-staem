import React from "react";

import { useSelector } from "react-redux";

import {
  ActionIcon,
  AspectRatio,
  Badge,
  Button,
  Card,
  Drawer,
  Grid,
  Group,
  Image,
  Stack,
  Text,
} from "@mantine/core";
import { showNotification } from "@mantine/notifications";
import {
  IconCircleCheck,
  IconCircleX,
  IconCreditCard,
  IconX,
} from "@tabler/icons";

import * as cartApi from "../api/cart";
import PriceDisplay from "./PriceDisplay";
import * as actions from "../store/shared/cart.actions";
import * as selectors from "../store/shared/cart.selectors";
import * as articleDetailsActions from "../pages/ArticleDetails/ArticleDetails.actions";
import * as articleDetailsSelectors from "../pages/ArticleDetails/ArticleDetails.selectors";
import * as userSelectors from "../store/shared/user.selectors";
import { useAppDispatch } from "../store/store";

type CartDrawerProps = {
  opened: boolean;
  onClose: () => void;
};

export default function CartDrawer({ opened, onClose }: CartDrawerProps) {
  return (
    <Drawer
      opened={opened}
      onClose={onClose}
      title="Cart"
      padding="xl"
      position="left"
      size="xl"
    >
      <DrawerContent />
    </Drawer>
  );
}

function DrawerContent() {
  const dispatch = useAppDispatch();

  const items = useSelector(selectors.items);
  const userPermissions = useSelector(userSelectors.permissions);

  const onDeleteItem = (id: number) => {
    dispatch(actions.removeItem(id));
  };

  if (!userPermissions.includes("create:cart")) {
    return null;
  }

  if (items.length === 0) {
    return <Text>Add items to your shopping cart</Text>;
  }

  return (
    <Stack spacing={10}>
      {items.map((item) => (
        <Card py={0} px={5} key={item.id}>
          <Grid gutter={10}>
            <Grid.Col span={4}>
              <AspectRatio ratio={16 / 9}>
                <Image src={item.pictureUrl} />
              </AspectRatio>
            </Grid.Col>
            <Grid.Col span={8}>
              <Stack p={0} style={{ position: "relative" }}>
                <Text pt={7}>{item.title}</Text>
                <Group position="apart">
                  <Badge size="sm">
                    <PriceDisplay {...item} />
                  </Badge>
                </Group>
                <ActionIcon
                  onClick={() => onDeleteItem(item.id)}
                  style={{ position: "absolute", top: 5, right: 0 }}
                >
                  <IconCircleX color="red" />
                </ActionIcon>
              </Stack>
            </Grid.Col>
          </Grid>
        </Card>
      ))}

      <CartActions />
    </Stack>
  );
}

function CartActions() {
  const dispatch = useAppDispatch();

  const items = useSelector(selectors.items);
  const articleDetails = useSelector(articleDetailsSelectors.result);

  const onClear = () => {
    dispatch(actions.clear());
    showNotification({
      message: "Cart cleared",
    });
  };

  const onBuy = async () => {
    try {
      await cartApi.create({ articles: items.map((item) => item.id) });

      showNotification({
        message: "Items successfully purchased",
        color: "green",
        icon: <IconCircleCheck />,
      });

      dispatch(actions.clear());

      if (articleDetails?.id) {
        dispatch(articleDetailsActions.findById(articleDetails.id));
      }
    } catch (error) {
      showNotification({
        message: "Error purchasing items",
        color: "red",
        icon: <IconCircleX />,
      });
    }
  };

  if (items.length === 0) {
    return null;
  }

  return (
    <Group position="right" mt={20}>
      <Button color="red" onClick={onClear} leftIcon={<IconX />}>
        Clear Cart
      </Button>
      <Button onClick={onBuy} leftIcon={<IconCreditCard />}>
        Buy Items
      </Button>
    </Group>
  );
}
